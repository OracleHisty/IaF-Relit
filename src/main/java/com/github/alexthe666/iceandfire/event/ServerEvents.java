package com.github.alexthe666.iceandfire.event;

import com.github.alexthe666.iceandfire.IafConfig;
import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.datagen.tags.IafItemTags;
import com.github.alexthe666.iceandfire.entity.*;
import com.github.alexthe666.iceandfire.entity.ai.AiDebug;
import com.github.alexthe666.iceandfire.entity.ai.VillagerAIFearUntamed;
import com.github.alexthe666.iceandfire.entity.props.EntityDataProvider;
import com.github.alexthe666.iceandfire.entity.util.IAnimalFear;
import com.github.alexthe666.iceandfire.entity.util.IVillagerFear;
import com.github.alexthe666.iceandfire.item.*;
import com.github.alexthe666.iceandfire.message.MessagePlayerHitMultipart;
import com.github.alexthe666.iceandfire.message.MessageSyncPath;
import com.github.alexthe666.iceandfire.misc.IafDamageRegistry;
import com.github.alexthe666.iceandfire.misc.IafTagRegistry;
import com.github.alexthe666.iceandfire.pathfinding.raycoms.Pathfinding;
import com.github.alexthe666.iceandfire.pathfinding.raycoms.pathjobs.AbstractPathJob;
import com.github.alexthe666.iceandfire.world.gen.WorldGenFireDragonCave;
import com.github.alexthe666.iceandfire.world.gen.WorldGenIceDragonCave;
import com.github.alexthe666.iceandfire.world.gen.WorldGenLightningDragonCave;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.AbstractChestBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Mod.EventBusSubscriber(modid = IceAndFire.MODID)
public class ServerEvents {

    public static final UUID ALEX_UUID = UUID.fromString("71363abe-fd03-49c9-940d-aae8b8209b7c");
    // FIXME :: No check for shouldFear()?
    private static final Predicate<LivingEntity> VILLAGER_FEAR = entity -> entity instanceof IVillagerFear;
    private final Random rand = new Random();

    private static boolean isInEntityTag(ResourceLocation loc, EntityType<?> type) {
        return type.is(Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.tags()).createTagKey(loc));
    }

    public static boolean fearsDragons(Entity entity) {
        return entity != null && isInEntityTag(IafTagRegistry.FEAR_DRAGONS, entity.getType());
    }

    public static boolean isRidingOrBeingRiddenBy(final Entity first, final Entity entityIn) {
        if (first == null || entityIn == null) {
            return false;
        }

        for (final Entity entity : first.getPassengers()) {
            if (entity.equals(entityIn) || isRidingOrBeingRiddenBy(entity, entityIn)) {
                return true;
            }
        }

        return false;
    }

    @SubscribeEvent
    public void onArrowCollide(final ProjectileImpactEvent event) {
        if (event.getRayTraceResult() instanceof EntityHitResult result) {
            Entity shotEntity = result.getEntity();

            if (event.getEntity() instanceof AbstractArrow arrow && arrow.getOwner() != null) {
                Entity shootingEntity = arrow.getOwner();

                if (shootingEntity instanceof LivingEntity && isRidingOrBeingRiddenBy(shootingEntity, shotEntity)) {
                    if (shotEntity instanceof TamableAnimal tamable && tamable.isTame() && shotEntity.isAlliedTo(shootingEntity)) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    private static final String[] VILLAGE_TYPES = new String[]{"plains", "desert", "snowy", "savanna", "taiga"};

    @SubscribeEvent
    public static void addNewVillageBuilding(final ServerAboutToStartEvent event) {
        if (IafConfig.villagerHouseWeight > 0) {
            Registry<StructureTemplatePool> templatePoolRegistry = event.getServer().registryAccess().registry(Registries.TEMPLATE_POOL).orElseThrow();
            Registry<StructureProcessorList> processorListRegistry = event.getServer().registryAccess().registry(Registries.PROCESSOR_LIST).orElseThrow();
            for (String type : VILLAGE_TYPES) {
                IafVillagerRegistry.addBuildingToPool(templatePoolRegistry, processorListRegistry, new ResourceLocation("village/" + type + "/houses"), "iceandfire:village/" + type + "_scriber_1", IafConfig.villagerHouseWeight);
            }
        }

    }

    @SubscribeEvent
    public void onPlayerAttackMob(AttackEntityEvent event) {
        if (event.getTarget() instanceof EntityMutlipartPart && event.getEntity() instanceof Player) {
            event.setCanceled(true);
            Entity parent = ((EntityMutlipartPart) event.getTarget()).getParent();
            try {
                //If the attacked entity is the parent itself parent will be null and also doesn't have to be attacked
                if (parent != null)
                    ((Player) event.getEntity()).attack(parent);
            } catch (Exception e) {
                IceAndFire.LOGGER.warn("Exception thrown while interacting with entity.", e);
            }
            int extraData = 0;

            if (event.getTarget().level().isClientSide && parent != null) {
                IceAndFire.NETWORK_WRAPPER.sendToServer(new MessagePlayerHitMultipart(parent.getId(), extraData));
            }
        }
    }

    @SubscribeEvent
    public void onEntityFall(final LivingFallEvent event) {
        if (event.getEntity() instanceof Player) {
            EntityDataProvider.getCapability(event.getEntity()).ifPresent(data -> {
                if (data.miscData.hasDismounted) {
                    event.setDamageMultiplier(0);
                    data.miscData.setDismounted(false);
                }
            });
        }
    }


    @SubscribeEvent
    public void onEntityDamage(LivingHurtEvent event) {
        if (event.getSource().is(IafDamageRegistry.DRAGON_FIRE_TYPE) || event.getSource().is(IafDamageRegistry.DRAGON_ICE_TYPE) || event.getSource().is(IafDamageRegistry.DRAGON_LIGHTNING_TYPE)) {
            float multi = 1;
            if (event.getEntity().getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof DragonArmor) multi -= 0.1f;
            if (event.getEntity().getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof DragonArmor) multi -= 0.3f;
            if (event.getEntity().getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof DragonArmor) multi -= 0.2f;
            if (event.getEntity().getItemBySlot(EquipmentSlot.FEET).getItem() instanceof DragonArmor) multi -= 0.1f;
            event.setAmount(event.getAmount() * multi);
        }
    }

    @SubscribeEvent
    public void onEntityDrop(LivingDropsEvent event) {
        if (event.getEntity() instanceof WitherSkeleton) {
            event.getDrops().add(new ItemEntity(event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(),
                    new ItemStack(IafItemRegistry.WITHERBONE.get(), event.getEntity().getRandom().nextInt(2))));
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void makeItemDropsFireImmune(final LivingDropsEvent event) {
        boolean makeFireImmune = false;

        if (event.getSource().getDirectEntity() instanceof LightningBolt bolt && bolt.getTags().contains(BOLT_DONT_DESTROY_LOOT)) {
            makeFireImmune = true;
        } else if (event.getSource().getEntity() instanceof Player player && player.getItemInHand(player.getUsedItemHand()).is(IafItemTags.MAKE_ITEM_DROPS_FIREIMMUNE)) {
            makeFireImmune = true;
        }

        if (makeFireImmune) {
            Set<ItemEntity> fireImmuneDrops = event.getDrops().stream().map(itemEntity -> new ItemEntity(itemEntity.level(), itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), itemEntity.getItem()) {
                @Override
                public boolean fireImmune() {
                    return true;
                }
            }).collect(Collectors.toSet());

            event.getDrops().clear();
            event.getDrops().addAll(fireImmuneDrops);
        }
    }

    @SubscribeEvent
    public void onLivingAttacked(final LivingAttackEvent event) {
        if (event.getSource() != null && event.getSource().getEntity() != null) {
            Entity attacker = event.getSource().getEntity();

            if (attacker instanceof LivingEntity) {
                EntityDataProvider.getCapability(attacker).ifPresent(data -> {
                    if (data.miscData.loveTicks > 0) {
                        event.setCanceled(true);
                    }
                });
            }
        }

    }

    @SubscribeEvent
    public void onEntityUseItem(PlayerInteractEvent.RightClickItem event) {
        if (event.getEntity() != null && event.getEntity().getXRot() > 87 && event.getEntity().getVehicle() != null && event.getEntity().getVehicle() instanceof EntityDragonBase) {
            ((EntityDragonBase) event.getEntity().getVehicle()).mobInteract(event.getEntity(), event.getHand());
        }
/*        if (event.getEntity() instanceof EntityDragonBase && !event.getEntity().isAlive()) {
            event.setResult(Event.Result.DENY);
            ((EntityDragonBase) event.getEntityLiving()).mobInteract(event.getPlayer(), event.getHand());
        }*/
    }

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingTickEvent event) {
        if (AiDebug.isEnabled() && event.getEntity() instanceof Mob && AiDebug.contains((Mob) event.getEntity())) {
            AiDebug.logData();
        }
    }

    @SubscribeEvent
    public void onEntityInteract(final PlayerInteractEvent.EntityInteract event) {
        // Handle chain removal
        if (event.getTarget() instanceof LivingEntity target) {
            EntityDataProvider.getCapability(target).ifPresent(data -> {
                if (data.chainData.isChainedTo(event.getEntity())) {
                    data.chainData.removeChain(event.getEntity());

                    if (!event.getLevel().isClientSide()) {
                        event.getTarget().spawnAtLocation(IafItemRegistry.CHAIN.get(), 1);
                    }

                    event.setCanceled(true);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
            });
        }

        // Handle debug path render
        if (!event.getLevel().isClientSide() && event.getTarget() instanceof Mob && event.getItemStack().getItem() == Items.STICK) {
            if (AiDebug.isEnabled())
                AiDebug.addEntity((Mob) event.getTarget());
            if (Pathfinding.isDebug()) {
                if (AbstractPathJob.trackingMap.getOrDefault(event.getEntity(), UUID.randomUUID()).equals(event.getTarget().getUUID())) {
                    AbstractPathJob.trackingMap.remove(event.getEntity());
                    IceAndFire.sendMSGToPlayer(new MessageSyncPath(new HashSet<>(), new HashSet<>(), new HashSet<>()), (ServerPlayer) event.getEntity());
                } else {
                    AbstractPathJob.trackingMap.put(event.getEntity(), event.getTarget().getUUID());
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerRightClick(PlayerInteractEvent.RightClickBlock event) {
        if (event.getEntity() != null && (event.getLevel().getBlockState(event.getPos()).getBlock() instanceof AbstractChestBlock) && !event.getEntity().isCreative()) {
            float dist = IafConfig.dragonGoldSearchLength;
            final List<Entity> list = event.getLevel().getEntities(event.getEntity(), event.getEntity().getBoundingBox().inflate(dist, dist, dist));
            if (!list.isEmpty()) {
                for (final Entity entity : list) {
                    if (entity instanceof EntityDragonBase dragon) {
                        if (!dragon.isTame() && !dragon.isModelDead() && !dragon.isOwnedBy(event.getEntity())) {
                            dragon.setInSittingPose(false);
                            dragon.setOrderedToSit(false);
                            dragon.setTarget(event.getEntity());
                        }
                    }
                }
            }
        }
        if (event.getLevel().getBlockState(event.getPos()).getBlock() instanceof WallBlock) {
            ItemChain.attachToFence(event.getEntity(), event.getLevel(), event.getPos());
        }
    }

    @SubscribeEvent
    public void onBreakBlock(BlockEvent.BreakEvent event) {
        if (event.getPlayer() != null && (event.getState().getBlock() instanceof AbstractChestBlock || event.getState().getBlock() == IafBlockRegistry.GOLD_PILE.get() || event.getState().getBlock() == IafBlockRegistry.SILVER_PILE.get() || event.getState().getBlock() == IafBlockRegistry.COPPER_PILE.get())) {
            final float dist = IafConfig.dragonGoldSearchLength;
            List<Entity> list = event.getLevel().getEntities(event.getPlayer(), event.getPlayer().getBoundingBox().inflate(dist, dist, dist));
            if (list.isEmpty()) return;

            for (Entity entity : list) {
                if (entity instanceof EntityDragonBase dragon) {
                    if (!dragon.isTame() && !dragon.isModelDead() && !dragon.isOwnedBy(event.getPlayer()) && !event.getPlayer().isCreative()) {
                        dragon.setInSittingPose(false);
                        dragon.setOrderedToSit(false);
                        dragon.setTarget(event.getPlayer());
                    }
                }
            }
        }
    }

    //@SubscribeEvent // FIXME :: Unused
    public static void onChestGenerated(LootTableLoadEvent event) {
        final ResourceLocation eventName = event.getName();
        final boolean condition1 = eventName.equals(BuiltInLootTables.SIMPLE_DUNGEON)
                || eventName.equals(BuiltInLootTables.ABANDONED_MINESHAFT)
                || eventName.equals(BuiltInLootTables.DESERT_PYRAMID)
                || eventName.equals(BuiltInLootTables.JUNGLE_TEMPLE)
                || eventName.equals(BuiltInLootTables.STRONGHOLD_CORRIDOR)
                || eventName.equals(BuiltInLootTables.STRONGHOLD_CROSSING);

        if (condition1 || eventName.equals(BuiltInLootTables.VILLAGE_CARTOGRAPHER)) {
            LootPoolEntryContainer.Builder item = LootItem.lootTableItem(IafItemRegistry.MANUSCRIPT.get()).setQuality(20).setWeight(5);
            LootPool.Builder builder = new LootPool.Builder().name("iaf_manuscript").add(item).when(LootItemRandomChanceCondition.randomChance(0.35f)).setRolls(UniformGenerator.between(1, 4)).setBonusRolls(UniformGenerator.between(0, 3));
            event.getTable().addPool(builder.build());
        }
        if (condition1
                || eventName.equals(BuiltInLootTables.IGLOO_CHEST)
                || eventName.equals(BuiltInLootTables.WOODLAND_MANSION)
                || eventName.equals(BuiltInLootTables.VILLAGE_TOOLSMITH)
                || eventName.equals(BuiltInLootTables.VILLAGE_ARMORER)) {


            LootPoolEntryContainer.Builder item = LootItem.lootTableItem(IafItemRegistry.SILVER_INGOT.get()).setQuality(15).setWeight(12);
            LootPool.Builder builder = new LootPool.Builder().name("iaf_silver_ingot").add(item).when(LootItemRandomChanceCondition.randomChance(0.5f)).setRolls(UniformGenerator.between(1, 3)).setBonusRolls(UniformGenerator.between(0, 3));
            event.getTable().addPool(builder.build());

        }
    }

    @SubscribeEvent
    public void onPlayerLeaveEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() != null && !event.getEntity().getPassengers().isEmpty()) {
            for (Entity entity : event.getEntity().getPassengers()) {
                entity.stopRiding();
            }
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorld(MobSpawnEvent.FinalizeSpawn event) {
        Mob mob = event.getEntity();
        try {
            if (fearsDragons(mob) && IafConfig.animalsFearDragons) {
                mob.goalSelector.addGoal(1, new VillagerAIFearUntamed((PathfinderMob) mob, LivingEntity.class, 30, 1.0D, 0.5D, entity -> entity instanceof IAnimalFear && ((IAnimalFear) entity).shouldAnimalsFear(mob)));
            }
        } catch (Exception e) {
            IceAndFire.LOGGER.warn("Tried to add unique behaviors to vanilla mobs and encountered an error");
        }
    }

    @SubscribeEvent
    public void onVillagerTrades(VillagerTradesEvent event) {
        if (event.getType() == IafVillagerRegistry.SCRIBE.get()) {
            IafVillagerRegistry.addScribeTrades(event.getTrades());
        }
    }

    public static String BOLT_DONT_DESTROY_LOOT = "iceandfire.bolt_skip_loot";

    @SubscribeEvent
    public void onLightningHit(final EntityStruckByLightningEvent event) {
        if ((event.getEntity() instanceof ItemEntity || event.getEntity() instanceof ExperienceOrb) && event.getLightning().getTags().contains(BOLT_DONT_DESTROY_LOOT)) {
            event.setCanceled(true);
        } else if (event.getLightning().getTags().contains(event.getEntity().getStringUUID())) {
            event.setCanceled(true);
        }
    }
}
