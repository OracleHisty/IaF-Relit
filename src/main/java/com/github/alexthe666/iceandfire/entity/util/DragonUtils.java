package com.github.alexthe666.iceandfire.entity.util;

import com.github.alexthe666.iceandfire.IafConfig;
import com.github.alexthe666.iceandfire.datagen.tags.IafBlockTags;
import com.github.alexthe666.iceandfire.entity.*;
import com.github.alexthe666.iceandfire.misc.IafTagRegistry;
import com.google.common.base.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

public class DragonUtils {
    public static BlockPos getBlockInViewEscort(EntityDragonBase dragon) {
        BlockPos escortPos = dragon.getEscortPosition();
        BlockPos ground = dragon.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, escortPos);
        int distFromGround = escortPos.getY() - ground.getY();
        for (int i = 0; i < 10; i++) {
            BlockPos pos = new BlockPos(escortPos.getX() + dragon.getRandom().nextInt(IafConfig.dragonWanderFromHomeDistance) - IafConfig.dragonWanderFromHomeDistance / 2,
                (distFromGround > 16 ? escortPos.getY() : escortPos.getY() + 8 + dragon.getRandom().nextInt(16)),
                (escortPos.getZ() + dragon.getRandom().nextInt(IafConfig.dragonWanderFromHomeDistance) - IafConfig.dragonWanderFromHomeDistance / 2));
            if (dragon.getDistanceSquared(Vec3.atCenterOf(pos)) > 6 && !dragon.isTargetBlocked(Vec3.atCenterOf(pos))) {
                return pos;
            }
        }
        return null;
    }

    public static BlockPos getWaterBlockInViewEscort(EntityDragonBase dragon) {
        // In water escort
        BlockPos inWaterEscortPos = dragon.getEscortPosition();
        // We don't need to get too close
        if (Math.abs(dragon.getX() - inWaterEscortPos.getX()) < dragon.getBoundingBox().getXsize()
                && Math.abs(dragon.getZ() - inWaterEscortPos.getZ()) < dragon.getBoundingBox().getZsize()) {
            return dragon.blockPosition();
        }
        // Takes off if the escort position is no longer in water, mainly for using elytra to fly out of the water
        if (inWaterEscortPos.getY() - dragon.getY() > 8 + dragon.getYNavSize() && !dragon.level().getFluidState(inWaterEscortPos.below()).is(FluidTags.WATER)) {
            dragon.setHovering(true);
        }
        // Swim directly to the escort position
        return inWaterEscortPos;
    }

    public static BlockPos getBlockInView(EntityDragonBase dragon) {
        float radius = 12 * (0.7F * dragon.getRenderSize() / 3);
        float neg = dragon.getRandom().nextBoolean() ? 1 : -1;
        float renderYawOffset = dragon.yBodyRot;
        if (dragon.hasHomePosition && dragon.homePos != null) {
            BlockPos dragonPos = dragon.blockPosition();
            BlockPos ground = dragon.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, dragonPos);
            int distFromGround = (int) dragon.getY() - ground.getY();
            for (int i = 0; i < 10; i++) {
                BlockPos homePos = dragon.homePos.getPosition();
                BlockPos pos = new BlockPos(homePos.getX() + dragon.getRandom().nextInt(IafConfig.dragonWanderFromHomeDistance * 2) - IafConfig.dragonWanderFromHomeDistance, (distFromGround > 16 ? (int) Math.min(IafConfig.maxDragonFlight, dragon.getY() + dragon.getRandom().nextInt(16) - 8) : (int) dragon.getY() + dragon.getRandom().nextInt(16) + 1), (homePos.getZ() + dragon.getRandom().nextInt(IafConfig.dragonWanderFromHomeDistance * 2) - IafConfig.dragonWanderFromHomeDistance));
                if (dragon.getDistanceSquared(Vec3.atCenterOf(pos)) > 6 && !dragon.isTargetBlocked(Vec3.atCenterOf(pos))) {
                    return pos;
                }
            }
        }
        float angle = (0.01745329251F * renderYawOffset) + 3.15F + (dragon.getRandom().nextFloat() * neg);
        double extraX = radius * Mth.sin((float) (Math.PI + angle));
        double extraZ = radius * Mth.cos(angle);
        BlockPos radialPos = BlockPos.containing(dragon.getX() + extraX, 0, dragon.getZ() + extraZ);
        BlockPos ground = dragon.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, radialPos);
        int distFromGround = (int) dragon.getY() - ground.getY();
        BlockPos newPos = radialPos.above(distFromGround > 16 ? (int) Math.min(IafConfig.maxDragonFlight, dragon.getY() + dragon.getRandom().nextInt(16) - 8) : (int) dragon.getY() + dragon.getRandom().nextInt(16) + 1);
        BlockPos pos = dragon.doesWantToLand() ? ground : newPos;
        if (dragon.getDistanceSquared(Vec3.atCenterOf(newPos)) > 6 && !dragon.isTargetBlocked(Vec3.atCenterOf(newPos))) {
            return pos;
        }
        return null;
    }

    public static BlockPos getWaterBlockInView(EntityDragonBase dragon) {
        float radius = 0.75F * (0.7F * dragon.getRenderSize() / 3) * -7 - dragon.getRandom().nextInt(dragon.getDragonStage() * 6);
        float neg = dragon.getRandom().nextBoolean() ? 1 : -1;
        float angle = (0.01745329251F * dragon.yBodyRot) + 3.15F + (dragon.getRandom().nextFloat() * neg);
        double extraX = radius * Mth.sin((float) (Math.PI + angle));
        double extraZ = radius * Mth.cos(angle);
        BlockPos radialPos = BlockPos.containing(dragon.getX() + extraX, 0, dragon.getZ() + extraZ);
        BlockPos ground = dragon.level().getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, radialPos);
        int distFromGround = (int) dragon.getY() - ground.getY();
        BlockPos newPos = radialPos.above(distFromGround > 16 ? (int) Math.min(IafConfig.maxDragonFlight, dragon.getY() + dragon.getRandom().nextInt(16) - 8) : (int) dragon.getY() + dragon.getRandom().nextInt(16) + 1);
        BlockPos pos = dragon.doesWantToLand() ? ground : newPos;
        BlockPos surface = dragon.level().getFluidState(newPos.below(2)).is(FluidTags.WATER) ? newPos.below(dragon.getRandom().nextInt(10) + 1) : newPos;
        if (dragon.getDistanceSquared(Vec3.atCenterOf(surface)) > 6 && dragon.level().getFluidState(surface).is(FluidTags.WATER)) {
            return surface;
        }
        return null;
    }

    public static LivingEntity riderLookingAtEntity(LivingEntity dragon, LivingEntity rider, double dist) {
        Vec3 Vector3d = rider.getEyePosition(1.0F);
        Vec3 Vector3d1 = rider.getViewVector(1.0F);
        Vec3 Vector3d2 = Vector3d.add(Vector3d1.x * dist, Vector3d1.y * dist, Vector3d1.z * dist);
        double d1 = dist;
        Entity pointedEntity = null;
        List<Entity> list = rider.level().getEntities(rider, rider.getBoundingBox().expandTowards(Vector3d1.x * dist, Vector3d1.y * dist, Vector3d1.z * dist).inflate(1.0D, 1.0D, 1.0D), new Predicate<Entity>() {
            @Override
            public boolean apply(@Nullable Entity entity) {
                if (onSameTeam(dragon, entity)) {
                    return false;
                }
                return entity != null && entity.isPickable() && entity instanceof LivingEntity && !entity.is(dragon) && !entity.isAlliedTo(dragon) && (!(entity instanceof IDeadMob) || !((IDeadMob) entity).isMobDead());
            }
        });
        double d2 = d1;
        for (int j = 0; j < list.size(); ++j) {
            Entity entity1 = list.get(j);
            AABB axisalignedbb = entity1.getBoundingBox().inflate((double) entity1.getPickRadius() + 2F);
            Vec3 raytraceresult = axisalignedbb.clip(Vector3d, Vector3d2).orElse(net.minecraft.world.phys.Vec3.ZERO);

            if (axisalignedbb.contains(Vector3d)) {
                if (d2 >= 0.0D) {
                    pointedEntity = entity1;
                    d2 = 0.0D;
                }
            } else if (raytraceresult != null) {
                double d3 = Vector3d.distanceTo(raytraceresult);

                if (d3 < d2 || d2 == 0.0D) {
                    if (entity1.getRootVehicle() == rider.getRootVehicle() && !rider.canRiderInteract()) {
                        if (d2 == 0.0D) {
                            pointedEntity = entity1;
                        }
                    } else {
                        pointedEntity = entity1;
                        d2 = d3;
                    }
                }
            }
        }
        return (LivingEntity) pointedEntity;
    }

    public static boolean canTameDragonAttack(TamableAnimal dragon, Entity entity) {
        if (isFleeingDragonTarget(entity)) {
            return false;
        }
        if (entity instanceof AbstractVillager || entity instanceof AbstractGolem || entity instanceof Player) {
            return false;
        }
        if (entity instanceof TamableAnimal) {
            return !((TamableAnimal) entity).isTame();
        }
        return true;
    }

    public static boolean isFleeingDragonTarget(Entity entity) {
        var tags =  ForgeRegistries.ENTITY_TYPES.tags();
        if (tags == null)
            return false;
        return entity.getType().is(tags.createTagKey(IafTagRegistry.FLEES_DRAGONS));
    }

    public static boolean isAnimaniaMob(Entity entity) {
        return false;
    }

    public static boolean isDragonTargetable(Entity entity, ResourceLocation tag) {
        return entity.getType().is(ForgeRegistries.ENTITY_TYPES.tags().createTagKey(tag));
    }

    public static String getDimensionName(Level world) {
        return world.dimension().location().toString();
    }

    public static boolean isInHomeDimension(EntityDragonBase dragonBase) {
        return (dragonBase.getHomeDimensionName() == null || getDimensionName(dragonBase.level()).equals(dragonBase.getHomeDimensionName()));
    }

    public static boolean canDragonBreak(final BlockState state, final Entity entity) {
        if (!ForgeEventFactory.getMobGriefingEvent(entity.level(), entity)) {
            return false;
        }

        Block block = state.getBlock();

        return block.getExplosionResistance() < 1200 && !state.is(IafBlockTags.DRAGON_BLOCK_BREAK_BLACKLIST);
    }

    public static boolean hasSameOwner(TamableAnimal animal, Entity entity) {
        if (entity instanceof TamableAnimal tameable) {
            return tameable.getOwnerUUID() != null && animal.getOwnerUUID() != null && tameable.getOwnerUUID().equals(animal.getOwnerUUID());
        }
        return false;
    }

    public static boolean isAlive(final LivingEntity entity) {
        if (entity instanceof EntityDragonBase dragon && dragon.isMobDead()) {
            return false;
        }

        return (!(entity instanceof IDeadMob deadMob) || !deadMob.isMobDead());
    }


    public static boolean canGrief(EntityDragonBase dragon) {
        if (dragon.isTame() && !IafConfig.tamedDragonGriefing) {
            return false;
        }

        return IafConfig.dragonGriefing < 2;

    }

    public static boolean canHostilesTarget(Entity entity) {
        if (entity instanceof Player && (entity.level().getDifficulty() == Difficulty.PEACEFUL || ((Player) entity).isCreative())) {
            return false;
        }
        if (entity instanceof EntityDragonBase && ((EntityDragonBase) entity).isMobDead()) {
            return false;
        } else {
            return entity instanceof LivingEntity && isAlive((LivingEntity) entity);
        }
    }

    public static boolean onSameTeam(Entity entity1, Entity entity2) {
        Entity owner1 = null;
        Entity owner2 = null;
        boolean def = entity1.isAlliedTo(entity2);
        if (entity1 instanceof TamableAnimal) {
            owner1 = ((TamableAnimal) entity1).getOwner();
        }
        if (entity2 instanceof TamableAnimal) {
            owner2 = ((TamableAnimal) entity2).getOwner();
        }
        if (entity1 instanceof EntityMutlipartPart) {
            Entity multipart = ((EntityMutlipartPart) entity1).getParent();
            if (multipart != null && multipart instanceof TamableAnimal) {
                owner1 = ((TamableAnimal) multipart).getOwner();
            }
        }
        if (entity2 instanceof EntityMutlipartPart) {
            Entity multipart = ((EntityMutlipartPart) entity2).getParent();
            if (multipart != null && multipart instanceof TamableAnimal) {
                owner2 = ((TamableAnimal) multipart).getOwner();
            }
        }
        if (owner1 != null && owner2 != null) {
            return owner1.is(owner2);
        }
        return def;
    }
}
