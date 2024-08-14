package com.github.alexthe666.iceandfire.entity;

import com.github.alexthe666.iceandfire.enums.EnumDragonEgg;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.alexthe666.iceandfire.misc.IafDamageRegistry;
import com.github.alexthe666.iceandfire.misc.IafSoundRegistry;
import com.github.alexthe666.iceandfire.misc.IafTagRegistry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static com.github.alexthe666.iceandfire.entity.DragonType.DragonLifeStages.TriState.FALSE;
import static com.github.alexthe666.iceandfire.entity.DragonType.DragonLifeStages.TriState.TRUE;

public enum DragonType implements StringRepresentable {
    FIRE("fire", false, IafSoundRegistry.FIRE,
            () -> ParticleTypes.FLAME,
            IafItemRegistry.FIRE_STEW,
            IafTagRegistry.FIRE_DRAGON_TARGETS,
            new DragonStats(),
            Map.of(
                    DragonLifeStages.HATCHLING, new ResourceLocation("iceandfire:textures/models/firedragon/skeleton_1.png"),
                    DragonLifeStages.CHILD, new ResourceLocation("iceandfire:textures/models/firedragon/skeleton_2.png"),
                    DragonLifeStages.TEEN, new ResourceLocation("iceandfire:textures/models/firedragon/skeleton_3.png"),
                    DragonLifeStages.ADULT, new ResourceLocation("iceandfire:textures/models/firedragon/skeleton_4.png"),
                    DragonLifeStages.ELDER, new ResourceLocation("iceandfire:textures/models/firedragon/skeleton_5.png")
            ),
            Optional.of(IafDamageRegistry.DRAGON_FIRE_TYPE),
            EnumDragonEgg.RED, EnumDragonEgg.GREEN, EnumDragonEgg.BRONZE, EnumDragonEgg.GRAY),
    ICE("ice", true, IafSoundRegistry.ICE,
            () -> ParticleTypes.SNOWFLAKE,
            IafItemRegistry.FROST_STEW,
            IafTagRegistry.ICE_DRAGON_TARGETS,
            new DragonStats(),
            Map.of(
                    DragonLifeStages.HATCHLING, new ResourceLocation("iceandfire:textures/models/icedragon/skeleton_1.png"),
                    DragonLifeStages.CHILD, new ResourceLocation("iceandfire:textures/models/icedragon/skeleton_2.png"),
                    DragonLifeStages.TEEN, new ResourceLocation("iceandfire:textures/models/icedragon/skeleton_3.png"),
                    DragonLifeStages.ADULT, new ResourceLocation("iceandfire:textures/models/icedragon/skeleton_4.png"),
                    DragonLifeStages.ELDER, new ResourceLocation("iceandfire:textures/models/icedragon/skeleton_5.png")
            ),
            Optional.of(IafDamageRegistry.DRAGON_LIGHTNING_TYPE),
            EnumDragonEgg.BLUE, EnumDragonEgg.WHITE, EnumDragonEgg.SAPPHIRE, EnumDragonEgg.SILVER),
    LIGHTNING("lightning", false, IafSoundRegistry.LIGHTNING,
            () -> ParticleTypes.RAIN,
            IafItemRegistry.LIGHTNING_STEW,
            IafTagRegistry.LIGHTNING_DRAGON_TARGETS,
            new DragonStats(),
            Map.of(
                    DragonLifeStages.HATCHLING, new ResourceLocation("iceandfire:textures/models/lightningdragon/skeleton/skeleton_1.png"),
                    DragonLifeStages.CHILD, new ResourceLocation("iceandfire:textures/models/lightningdragon/skeleton/skeleton_2.png"),
                    DragonLifeStages.TEEN, new ResourceLocation("iceandfire:textures/models/lightningdragon/skeleton/skeleton_3.png"),
                    DragonLifeStages.ADULT, new ResourceLocation("iceandfire:textures/models/lightningdragon/skeleton/skeleton_4.png"),
                    DragonLifeStages.ELDER, new ResourceLocation("iceandfire:textures/models/lightningdragon/skeleton/skeleton_5.png")
            ),
            Optional.of(IafDamageRegistry.DRAGON_LIGHTNING_TYPE),
            EnumDragonEgg.ELECTRIC, EnumDragonEgg.AMYTHEST, EnumDragonEgg.COPPER, EnumDragonEgg.BLACK);

    private final String name;
    private final boolean eatsFish;

    private final ResourceLocation femaleLoot;
    private final ResourceLocation maleLoot;
    private final ResourceLocation skeletonLoot;

    private final DragonStats stats;
    private final EnumDragonEgg[] eggs;
    private final IafSoundRegistry.DragonSoundGroup sounds;
    private final Supplier<Item> breedingFood;
    private final Supplier<ParticleOptions> deathParticle;
    private final ResourceLocation targetTag;
    private final Optional<ResourceKey<DamageType>> damageType;
    private final Map<DragonLifeStages, ResourceLocation> skeletonTextures;

    DragonType(String name, boolean eatsFish,
               IafSoundRegistry.DragonSoundGroup sounds,
               Supplier<ParticleOptions> deathParticle,
               Supplier<Item> breedingFood,
               ResourceLocation targetTag,
               DragonStats stats,
               Map<DragonLifeStages, ResourceLocation> skeletonTextures,
               Optional<ResourceKey<DamageType>> damageType,
               EnumDragonEgg... eggs) {
        this.name = name;
        this.eatsFish = eatsFish;
        this.sounds = sounds;
        this.femaleLoot = new ResourceLocation("iceandfire", "entities/dragon/%s_dragon_female".formatted(name));
        this.maleLoot = new ResourceLocation("iceandfire", "entities/dragon/%s_dragon_male".formatted(name));
        this.skeletonLoot = new ResourceLocation("iceandfire", "entities/dragon/%s_dragon_skeleton".formatted(name));
        this.deathParticle = deathParticle;
        this.breedingFood = breedingFood;
        this.targetTag = targetTag;
        this.damageType = damageType;
        this.stats = stats;
        this.eggs = eggs;
        this.skeletonTextures = skeletonTextures;
    }

    public static void init() {
    }

    public EnumDragonEgg getEgg(int variant) {
        if(variant > eggs.length) throw new RuntimeException("Egg variant is higher than existing ones. " + variant);
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

    public SoundEvent getSound(DragonLifeStages age, DragonSoundType soundType) {
        var Sound = this.sounds.lifeStages().getOrDefault(age, Collections.emptyMap()).getOrDefault(soundType, null);
        return Sound != null ? Sound : IafSoundRegistry.GENERIC.lifeStages().getOrDefault(age, Collections.emptyMap()).getOrDefault(soundType, SoundEvents.GOAT_SCREAMING_DEATH);
    }

    public ParticleOptions getDeathParticle() {
        return deathParticle.get();
    }

    public Supplier<Item> getBreedingFood() {
        return breedingFood;
    }

    public ResourceLocation targetTag() {
        return targetTag;
    }

    public DragonStats stats() {
        return stats;
    }

    public Optional<ResourceKey<DamageType>> damageType() {
        return damageType;
    }

    public ResourceLocation getSkeletonTexture(DragonLifeStages dragonStage) {
        return skeletonTextures.get(dragonStage);
    }

    public enum DragonLifeStages {
        EGG,
        HATCHLING,
        CHILD,
        TEEN,
        ADULT,
        ELDER;

        //sees if this LifeStage is younger than the paramater stage.
        public TriState younger(DragonLifeStages stage) {
            if(this == stage) return TriState.UNDEFINED;

            return switch (this) {
                case EGG -> TRUE;
                case HATCHLING -> stage == EGG ? FALSE : TRUE;
                case CHILD -> (stage == EGG || stage == HATCHLING) ? FALSE : TRUE;
                case TEEN -> (stage == ADULT || stage == ELDER) ? TRUE : FALSE;
                case ADULT -> stage == ELDER ? TRUE : FALSE;
                case ELDER -> FALSE;
            };
        }

        public enum TriState {
            TRUE, FALSE, UNDEFINED;

            public TriState invert() {
                return this == UNDEFINED ? UNDEFINED : this == TRUE ? FALSE : TRUE;
            }
        }

        public TriState older(DragonLifeStages stage) {
            return younger(stage).invert();
        }

        public static DragonLifeStages[] aliveStages() {
            return ALIVE_STAGES;
        }

        private static final DragonLifeStages[] ALIVE_STAGES;

        static {
            ALIVE_STAGES = new DragonLifeStages[] { EGG, HATCHLING, CHILD, TEEN, ADULT, ELDER};
        }
    }

    public enum DragonSoundType {
        IDLE,
        HURT,
        DEATH,
        ROAR;
    }

    public record DragonStats(double minimumDamage, double maximumDamage, double minimumHealth, double maximumHealth, double minimumSpeed, double maximumSpeed, double minimumArmor, double maximumArmor) {
        DragonStats() {
            this(1, 1 + 17, 500 * 0.04, 500, 0.15F, 0.4F, 1D, 20D);
        }
    }
}
