package com.github.alexthe666.iceandfire.item;

import com.github.alexthe666.iceandfire.entity.DragonType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ItemDragonFlesh extends ItemGenericFood {

    public final DragonType dragonType;

    public ItemDragonFlesh(DragonType dragonType) {
        super(8, 0.8F, true, false, false);
        this.dragonType = dragonType;
    }

    static String getNameForType(int dragonType) {
        return switch (dragonType) {
            case 0 -> "fire_dragon_flesh";
            case 1 -> "ice_dragon_flesh";
            case 2 -> "lightning_dragon_flesh";
            default -> "fire_dragon_flesh";
        };
    }

    @Override
    public void onFoodEaten(ItemStack stack, Level worldIn, LivingEntity livingEntity) {
        if (!worldIn.isClientSide) {
            switch (dragonType) {
                case FIRE -> livingEntity.setSecondsOnFire(5);
                case ICE -> livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
                default -> {
                    if (!livingEntity.level().isClientSide) {
                        LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(livingEntity.level());
                        lightningboltentity.moveTo(livingEntity.position());
                        if (!livingEntity.level().isClientSide) {
                            livingEntity.level().addFreshEntity(lightningboltentity);
                        }
                    }
                }
            }
        }
    }
}
