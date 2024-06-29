package com.github.alexthe666.iceandfire.entity;

import com.github.alexthe666.iceandfire.enums.EnumDragonEgg;
import com.github.alexthe666.iceandfire.misc.IafSoundRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Arrays;

public enum DragonType implements StringRepresentable {
    FIRE("fire", false,
            IafSoundRegistry.FIREDRAGON_TEEN_IDLE, IafSoundRegistry.FIREDRAGON_ADULT_IDLE, IafSoundRegistry.FIREDRAGON_CHILD_IDLE,
            IafSoundRegistry.FIREDRAGON_TEEN_HURT, IafSoundRegistry.FIREDRAGON_ADULT_HURT, IafSoundRegistry.FIREDRAGON_CHILD_HURT,
            IafSoundRegistry.FIREDRAGON_TEEN_DEATH, IafSoundRegistry.FIREDRAGON_ADULT_DEATH, IafSoundRegistry.FIREDRAGON_CHILD_DEATH,
            IafSoundRegistry.FIREDRAGON_TEEN_ROAR, IafSoundRegistry.FIREDRAGON_ADULT_ROAR, IafSoundRegistry.FIREDRAGON_CHILD_ROAR,
            EnumDragonEgg.RED, EnumDragonEgg.GREEN, EnumDragonEgg.BRONZE, EnumDragonEgg.GRAY),
    ICE("ice", true,
            IafSoundRegistry.ICEDRAGON_TEEN_IDLE, IafSoundRegistry.ICEDRAGON_ADULT_IDLE, IafSoundRegistry.ICEDRAGON_CHILD_IDLE,
            IafSoundRegistry.ICEDRAGON_TEEN_HURT, IafSoundRegistry.ICEDRAGON_ADULT_HURT, IafSoundRegistry.ICEDRAGON_CHILD_HURT,
            IafSoundRegistry.ICEDRAGON_TEEN_DEATH, IafSoundRegistry.ICEDRAGON_ADULT_DEATH, IafSoundRegistry.ICEDRAGON_CHILD_DEATH,
            IafSoundRegistry.ICEDRAGON_TEEN_ROAR, IafSoundRegistry.ICEDRAGON_ADULT_ROAR, IafSoundRegistry.ICEDRAGON_CHILD_ROAR,
            EnumDragonEgg.BLUE, EnumDragonEgg.WHITE, EnumDragonEgg.SAPPHIRE, EnumDragonEgg.SILVER),
    LIGHTNING("lightning", false,
            IafSoundRegistry.LIGHTNINGDRAGON_TEEN_IDLE, IafSoundRegistry.LIGHTNINGDRAGON_ADULT_IDLE, IafSoundRegistry.LIGHTNINGDRAGON_CHILD_IDLE,
            IafSoundRegistry.LIGHTNINGDRAGON_TEEN_HURT, IafSoundRegistry.LIGHTNINGDRAGON_ADULT_HURT, IafSoundRegistry.LIGHTNINGDRAGON_CHILD_HURT,
            IafSoundRegistry.LIGHTNINGDRAGON_TEEN_DEATH, IafSoundRegistry.LIGHTNINGDRAGON_ADULT_DEATH, IafSoundRegistry.LIGHTNINGDRAGON_CHILD_DEATH,
            IafSoundRegistry.LIGHTNINGDRAGON_TEEN_ROAR, IafSoundRegistry.LIGHTNINGDRAGON_ADULT_ROAR, IafSoundRegistry.LIGHTNINGDRAGON_CHILD_ROAR,
            EnumDragonEgg.ELECTRIC, EnumDragonEgg.AMYTHEST, EnumDragonEgg.COPPER, EnumDragonEgg.BLACK);

    private final String name;
    private final boolean eatsFish;
    private final SoundEvent teenIdle;
    private final SoundEvent adultIdle;
    private final SoundEvent childIdle;
    private final SoundEvent teenHurt;
    private final SoundEvent adultHurt;
    private final SoundEvent childHurt;
    private final SoundEvent teenDeath;
    private final SoundEvent adultDeath;
    private final SoundEvent childDeath;
    private final SoundEvent teenRoar;
    private final SoundEvent adultRoar;
    private final SoundEvent childRoar;

    private final ResourceLocation femaleLoot;
    private final ResourceLocation maleLoot;
    private final ResourceLocation skeletonLoot;
    private final EnumDragonEgg[] eggs;

    DragonType(String name, boolean eatsFish,
               SoundEvent teenIdle, SoundEvent adultIdle, SoundEvent childIdle,
               SoundEvent teenHurt, SoundEvent adultHurt, SoundEvent childHurt,
               SoundEvent teenDeath, SoundEvent adultDeath, SoundEvent childDeath,
               SoundEvent teenRoar, SoundEvent adultRoar, SoundEvent childRoar,
            EnumDragonEgg... eggs) {
        this.name = name;
        this.eatsFish = eatsFish;
        this.teenIdle = teenIdle;
        this.adultIdle = adultIdle;
        this.childIdle = childIdle;
        this.teenHurt = teenHurt;
        this.adultHurt = adultHurt;
        this.childHurt = childHurt;
        this.teenDeath = teenDeath;
        this.adultDeath = adultDeath;
        this.childDeath = childDeath;
        this.teenRoar = teenRoar;
        this.adultRoar = adultRoar;
        this.childRoar = childRoar;

        this.femaleLoot = new ResourceLocation("iceandfire", "entities/dragon/%s_dragon_female".formatted(name));
        this.maleLoot = new ResourceLocation("iceandfire", "entities/dragon/%s_dragon_male".formatted(name));
        this.skeletonLoot = new ResourceLocation("iceandfire", "entities/dragon/%s_dragon_skeleton".formatted(name));

        this.eggs = eggs;
    }

    public EnumDragonEgg getEgg(int variant) {
        System.out.println("eggbugs_gobrrt+: " + variant + " " + eggs.length);
        if(variant > eggs.length) throw new RuntimeException("Egg variant is higher than existing ones");
        return eggs[variant];
    }

    public static String getNameFromInt(int type){
        return switch (type) {
            case 2 -> "lightning";
            case 1 -> "ice";
            default -> "fire";
        };
    }

    public EntityType<? extends EntityDragonBase> getEntity() {
        if (this == LIGHTNING) {
            return IafEntityRegistry.LIGHTNING_DRAGON.get();
        } else if (this == ICE) {
            return IafEntityRegistry.ICE_DRAGON.get();
        }

        return IafEntityRegistry.FIRE_DRAGON.get();
    }

    public String getName() {
        return name;
    }

    public boolean eatsFish() {
        return eatsFish;
    }

    @Override
    public @NotNull String getSerializedName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public SoundEvent teenIdleSound() {
        return teenIdle;
    }

    public SoundEvent adultIdleSound() {
        return adultIdle;
    }

    public SoundEvent childIdleSound() {
        return adultIdle;
    }

    public SoundEvent teenHurtSound() {
        return childIdle;
    }

    public SoundEvent adultHurtSound() {
        return teenHurt;
    }

    public SoundEvent childHurtSound() {
        return adultHurt;
    }

    public SoundEvent teenDeathSound() {
        return childHurt;
    }

    public SoundEvent adultDeathSound() {
        return teenDeath;
    }

    public SoundEvent childDeathSound() {
        return adultDeath;
    }

    public SoundEvent teenRoarSound() {
        return childDeath;
    }

    public SoundEvent adultRoarSound() {
        return teenRoar;
    }

    public SoundEvent childRoarSound() {
        return childRoar;
    }

    public ResourceLocation femaleLoot() {
        return femaleLoot;
    }
    public ResourceLocation maleLoot() {
        return maleLoot;
    }
    public ResourceLocation skeletonLoot() {
        return skeletonLoot;
    }

    @Nonnull
    public EnumDragonEgg getRandomEgg(RandomSource random) {
        return eggs[random.nextInt(eggs.length)];
    }
}
