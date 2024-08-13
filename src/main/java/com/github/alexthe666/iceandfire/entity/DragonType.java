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
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

import static com.github.alexthe666.iceandfire.entity.DragonType.DragonLifeStages.TriState.FALSE;
import static com.github.alexthe666.iceandfire.entity.DragonType.DragonLifeStages.TriState.TRUE;

public enum DragonType implements StringRepresentable {
    FIRE("fire", false,
            Map.of(
                    DragonLifeStages.CHILD, Map.of(
                            DragonSoundType.IDLE, IafSoundRegistry.FIREDRAGON_CHILD_IDLE,
                            DragonSoundType.HURT, IafSoundRegistry.FIREDRAGON_CHILD_HURT,
                            DragonSoundType.DEATH, IafSoundRegistry.FIREDRAGON_CHILD_DEATH,
                            DragonSoundType.ROAR, IafSoundRegistry.FIREDRAGON_CHILD_ROAR),
                    DragonLifeStages.TEEN, Map.of(
                            DragonSoundType.IDLE, IafSoundRegistry.FIREDRAGON_TEEN_IDLE,
                            DragonSoundType.HURT, IafSoundRegistry.FIREDRAGON_TEEN_HURT,
                            DragonSoundType.DEATH, IafSoundRegistry.FIREDRAGON_TEEN_DEATH,
                            DragonSoundType.ROAR, IafSoundRegistry.FIREDRAGON_TEEN_ROAR),
                    DragonLifeStages.ADULT, Map.of(
                            DragonSoundType.IDLE, IafSoundRegistry.FIREDRAGON_ADULT_IDLE,
                            DragonSoundType.HURT, IafSoundRegistry.FIREDRAGON_ADULT_HURT,
                            DragonSoundType.DEATH, IafSoundRegistry.FIREDRAGON_ADULT_DEATH,
                            DragonSoundType.ROAR, IafSoundRegistry.FIREDRAGON_ADULT_ROAR)
            ),
            () -> ParticleTypes.FLAME,
            IafItemRegistry.FIRE_STEW,
            IafTagRegistry.FIRE_DRAGON_TARGETS,
            new DragonStats(),
            Map.of(
                    DragonType.DragonLifeStages.HATCHLING, new ResourceLocation("iceandfire:textures/models/firedragon/skeleton_1.png"),
                    DragonType.DragonLifeStages.CHILD, new ResourceLocation("iceandfire:textures/models/firedragon/skeleton_2.png"),
                    DragonType.DragonLifeStages.TEEN, new ResourceLocation("iceandfire:textures/models/firedragon/skeleton_3.png"),
                    DragonType.DragonLifeStages.ADULT, new ResourceLocation("iceandfire:textures/models/firedragon/skeleton_4.png"),
                    DragonType.DragonLifeStages.ELDER, new ResourceLocation("iceandfire:textures/models/firedragon/skeleton_5.png")
            ),
            Optional.of(IafDamageRegistry.DRAGON_FIRE_TYPE),
            EnumDragonEgg.RED, EnumDragonEgg.GREEN, EnumDragonEgg.BRONZE, EnumDragonEgg.GRAY),
    ICE("ice", true,
            Map.of(
                    DragonLifeStages.CHILD, Map.of(
                            DragonSoundType.IDLE, IafSoundRegistry.ICEDRAGON_CHILD_IDLE,
                            DragonSoundType.HURT, IafSoundRegistry.ICEDRAGON_CHILD_HURT,
                            DragonSoundType.DEATH, IafSoundRegistry.ICEDRAGON_CHILD_DEATH,
                            DragonSoundType.ROAR, IafSoundRegistry.ICEDRAGON_CHILD_ROAR),
                    DragonLifeStages.TEEN, Map.of(
                            DragonSoundType.IDLE, IafSoundRegistry.ICEDRAGON_TEEN_IDLE,
                            DragonSoundType.HURT, IafSoundRegistry.ICEDRAGON_TEEN_HURT,
                            DragonSoundType.DEATH, IafSoundRegistry.ICEDRAGON_TEEN_DEATH,
                            DragonSoundType.ROAR, IafSoundRegistry.ICEDRAGON_TEEN_ROAR),
                    DragonLifeStages.ADULT, Map.of(
                            DragonSoundType.IDLE, IafSoundRegistry.ICEDRAGON_ADULT_IDLE,
                            DragonSoundType.HURT, IafSoundRegistry.ICEDRAGON_ADULT_HURT,
                            DragonSoundType.DEATH, IafSoundRegistry.ICEDRAGON_ADULT_DEATH,
                            DragonSoundType.ROAR, IafSoundRegistry.ICEDRAGON_ADULT_ROAR)
            ),
            () -> ParticleTypes.SNOWFLAKE,
            IafItemRegistry.FROST_STEW,
            IafTagRegistry.ICE_DRAGON_TARGETS,
            new DragonStats(),
            Map.of(
                    DragonType.DragonLifeStages.HATCHLING, new ResourceLocation("iceandfire:textures/models/icedragon/skeleton_1.png"),
                    DragonType.DragonLifeStages.CHILD, new ResourceLocation("iceandfire:textures/models/icedragon/skeleton_2.png"),
                    DragonType.DragonLifeStages.TEEN, new ResourceLocation("iceandfire:textures/models/icedragon/skeleton_3.png"),
                    DragonType.DragonLifeStages.ADULT, new ResourceLocation("iceandfire:textures/models/icedragon/skeleton_4.png"),
                    DragonType.DragonLifeStages.ELDER, new ResourceLocation("iceandfire:textures/models/icedragon/skeleton_5.png")
            ),
            Optional.of(IafDamageRegistry.DRAGON_LIGHTNING_TYPE),
            EnumDragonEgg.BLUE, EnumDragonEgg.WHITE, EnumDragonEgg.SAPPHIRE, EnumDragonEgg.SILVER),
    LIGHTNING("lightning", false,
            Map.of(
                    DragonLifeStages.CHILD, Map.of(
                            DragonSoundType.IDLE, IafSoundRegistry.LIGHTNINGDRAGON_CHILD_IDLE,
                            DragonSoundType.HURT, IafSoundRegistry.LIGHTNINGDRAGON_CHILD_HURT,
                            DragonSoundType.DEATH, IafSoundRegistry.LIGHTNINGDRAGON_CHILD_DEATH,
                            DragonSoundType.ROAR, IafSoundRegistry.LIGHTNINGDRAGON_CHILD_ROAR),
                    DragonLifeStages.TEEN, Map.of(
                            DragonSoundType.IDLE, IafSoundRegistry.LIGHTNINGDRAGON_TEEN_IDLE,
                            DragonSoundType.HURT, IafSoundRegistry.LIGHTNINGDRAGON_TEEN_HURT,
                            DragonSoundType.DEATH, IafSoundRegistry.LIGHTNINGDRAGON_TEEN_DEATH,
                            DragonSoundType.ROAR, IafSoundRegistry.LIGHTNINGDRAGON_TEEN_ROAR),
                    DragonLifeStages.ADULT, Map.of(
                            DragonSoundType.IDLE, IafSoundRegistry.LIGHTNINGDRAGON_ADULT_IDLE,
                            DragonSoundType.HURT, IafSoundRegistry.LIGHTNINGDRAGON_ADULT_HURT,
                            DragonSoundType.DEATH, IafSoundRegistry.LIGHTNINGDRAGON_ADULT_DEATH,
                            DragonSoundType.ROAR, IafSoundRegistry.LIGHTNINGDRAGON_ADULT_ROAR)
            ),
            () -> ParticleTypes.RAIN,
            IafItemRegistry.LIGHTNING_STEW,
            IafTagRegistry.LIGHTNING_DRAGON_TARGETS,
            new DragonStats(),
            Map.of(
                    DragonType.DragonLifeStages.HATCHLING, new ResourceLocation("iceandfire:textures/models/lightningdragon/skeleton_1.png"),
                    DragonType.DragonLifeStages.CHILD, new ResourceLocation("iceandfire:textures/models/lightningdragon/skeleton_2.png"),
                    DragonType.DragonLifeStages.TEEN, new ResourceLocation("iceandfire:textures/models/lightningdragon/skeleton_3.png"),
                    DragonType.DragonLifeStages.ADULT, new ResourceLocation("iceandfire:textures/models/lightningdragon/skeleton_4.png"),
                    DragonType.DragonLifeStages.ELDER, new ResourceLocation("iceandfire:textures/models/lightningdragon/skeleton_5.png")
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
    private final Map<DragonLifeStages, Map<DragonSoundType, SoundEvent>> sounds;
    private final Supplier<Item> breedingFood;
    private Supplier<ParticleOptions> deathParticle;
    private ResourceLocation targetTag;
    private Optional<ResourceKey<DamageType>> damageType;
    private Map<DragonLifeStages, ResourceLocation> skeletonTextures;

    DragonType(String name, boolean eatsFish,
               Map<DragonLifeStages, Map<DragonSoundType, SoundEvent>> sounds,
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
        return this.sounds.get(age).get(soundType); //TODO: Generic default draogn sounds whenn switch to data driven occurs
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
        ROAR
    }

    public record DragonStats(double minimumDamage, double maximumDamage, double minimumHealth, double maximumHealth, double minimumSpeed, double maximumSpeed, double minimumArmor, double maximumArmor) {
        DragonStats() {
            this(1, 1 + 17, 500 * 0.04, 500, 0.15F, 0.4F, 1D, 20D);
        }
    }
}
