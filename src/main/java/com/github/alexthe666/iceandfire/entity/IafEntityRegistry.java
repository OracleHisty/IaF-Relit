package com.github.alexthe666.iceandfire.entity;

import com.github.alexthe666.iceandfire.IafConfig;
import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.config.BiomeConfig;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.world.ModifiableBiomeInfo;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.HashMap;

@Mod.EventBusSubscriber(modid = IceAndFire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IafEntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,
        IceAndFire.MODID);

    public static final RegistryObject<EntityType<EntityDragonPart>> DRAGON_MULTIPART = registerEntity(EntityType.Builder.<EntityDragonPart>of(EntityDragonPart::new, MobCategory.MISC).sized(0.5F, 0.5F).fireImmune().setCustomClientFactory(EntityDragonPart::new), "dragon_multipart");
    public static final RegistryObject<EntityType<EntitySlowPart>> SLOW_MULTIPART = registerEntity(EntityType.Builder.<EntitySlowPart>of(EntitySlowPart::new, MobCategory.MISC).sized(0.5F, 0.5F).fireImmune().setCustomClientFactory(EntitySlowPart::new), "multipart");
    public static final RegistryObject<EntityType<EntityDragonEgg>> DRAGON_EGG = registerEntity(EntityType.Builder.of(EntityDragonEgg::new, MobCategory.MISC).sized(0.45F, 0.55F).fireImmune(), "dragon_egg");
    public static final RegistryObject<EntityType<EntityDragonArrow>> DRAGON_ARROW = registerEntity(EntityType.Builder.<EntityDragonArrow>of(EntityDragonArrow::new, MobCategory.MISC).sized(0.5F, 0.5F).setCustomClientFactory(EntityDragonArrow::new), "dragon_arrow");
    public static final RegistryObject<EntityType<EntityDragonSkull>> DRAGON_SKULL = registerEntity(EntityType.Builder.of(EntityDragonSkull::new, MobCategory.MISC).sized(0.9F, 0.65F), "dragon_skull");
    public static final RegistryObject<EntityType<EntityFireDragon>> FIRE_DRAGON = registerEntity(EntityType.Builder.<EntityFireDragon>of(EntityFireDragon::new, MobCategory.CREATURE).sized(0.78F, 1.2F).fireImmune().setTrackingRange(256).clientTrackingRange(10), "fire_dragon");
    public static final RegistryObject<EntityType<EntityIceDragon>> ICE_DRAGON = registerEntity(EntityType.Builder.<EntityIceDragon>of(EntityIceDragon::new, MobCategory.CREATURE).sized(0.78F, 1.2F).setTrackingRange(256).clientTrackingRange(10), "ice_dragon");
    public static final RegistryObject<EntityType<EntityLightningDragon>> LIGHTNING_DRAGON = registerEntity(EntityType.Builder.<EntityLightningDragon>of(EntityLightningDragon::new, MobCategory.CREATURE).sized(0.78F, 1.2F).setTrackingRange(256).clientTrackingRange(10), "lightning_dragon");
    public static final RegistryObject<EntityType<EntityDragonFireCharge>> FIRE_DRAGON_CHARGE = registerEntity(EntityType.Builder.<EntityDragonFireCharge>of(EntityDragonFireCharge::new, MobCategory.MISC).sized(0.9F, 0.9F).setCustomClientFactory(EntityDragonFireCharge::new), "fire_dragon_charge");
    public static final RegistryObject<EntityType<EntityDragonIceCharge>> ICE_DRAGON_CHARGE = registerEntity(EntityType.Builder.<EntityDragonIceCharge>of(EntityDragonIceCharge::new, MobCategory.MISC).sized(0.9F, 0.9F).setCustomClientFactory(EntityDragonIceCharge::new), "ice_dragon_charge");
    public static final RegistryObject<EntityType<EntityDragonLightningCharge>> LIGHTNING_DRAGON_CHARGE = registerEntity(EntityType.Builder.<EntityDragonLightningCharge>of(EntityDragonLightningCharge::new, MobCategory.MISC).sized(0.9F, 0.9F).setCustomClientFactory(EntityDragonLightningCharge::new), "lightning_dragon_charge");
    public static final RegistryObject<EntityType<EntityChainTie>> CHAIN_TIE = registerEntity(EntityType.Builder.<EntityChainTie>of(EntityChainTie::new, MobCategory.MISC).sized(0.8F, 0.9F), "chain_tie");

    private static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(EntityType.Builder<T> builder, String entityName) {
        return ENTITIES.register(entityName, () -> builder.build(entityName));
    }

    @SubscribeEvent
    public static void bakeAttributes(EntityAttributeCreationEvent creationEvent) {
        creationEvent.put(DRAGON_EGG.get(), EntityDragonEgg.bakeAttributes().build());
        creationEvent.put(DRAGON_SKULL.get(), EntityDragonSkull.bakeAttributes().build());
        creationEvent.put(FIRE_DRAGON.get(), EntityFireDragon.bakeAttributes().build());
        creationEvent.put(ICE_DRAGON.get(), EntityIceDragon.bakeAttributes().build());
        creationEvent.put(LIGHTNING_DRAGON.get(), EntityLightningDragon.bakeAttributes().build());
    }

    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
    }

    public static HashMap<String, Boolean> LOADED_ENTITIES;
    static {
    	LOADED_ENTITIES = new HashMap<>();
        }
    }
