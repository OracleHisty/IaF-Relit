package com.github.alexthe666.iceandfire.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ServerConfig {

    public final ForgeConfigSpec.BooleanValue generateDragonSkeletons;
    public final ForgeConfigSpec.IntValue generateDragonSkeletonChance;
    public final ForgeConfigSpec.IntValue generateDragonDenChance;
    public final ForgeConfigSpec.IntValue generateDragonRoostChance;
    public final ForgeConfigSpec.IntValue dragonDenGoldAmount;
    public final ForgeConfigSpec.BooleanValue spawnGlaciers;
    public final ForgeConfigSpec.IntValue glacierSpawnChance;
    public final ForgeConfigSpec.IntValue oreToStoneRatioForDragonCaves;
    public final ForgeConfigSpec.IntValue dragonEggTime;
    public final ForgeConfigSpec.BooleanValue dragonGriefing;
    public final ForgeConfigSpec.BooleanValue tamedDragonGriefing;
    public final ForgeConfigSpec.IntValue dragonFlapNoiseDistance;
    public final ForgeConfigSpec.IntValue dragonFluteDistance;
    public final ForgeConfigSpec.DoubleValue dragonHealth;
    public final ForgeConfigSpec.IntValue dragonAttackDamage;
    public final ForgeConfigSpec.DoubleValue dragonAttackDamageFire;
    public final ForgeConfigSpec.DoubleValue dragonAttackDamageIce;
    public final ForgeConfigSpec.DoubleValue dragonAttackDamageLightning;
    public final ForgeConfigSpec.IntValue maxDragonFlight;
    public final ForgeConfigSpec.IntValue dragonGoldSearchLength;
    public final ForgeConfigSpec.BooleanValue canDragonsHealFromBiting;
    public final ForgeConfigSpec.BooleanValue canDragonsDespawn;
    public final ForgeConfigSpec.BooleanValue doDragonsSleep;
    public final ForgeConfigSpec.BooleanValue dragonDigWhenStuck;
    public final ForgeConfigSpec.IntValue dragonBreakBlockCooldown;
    public final ForgeConfigSpec.BooleanValue dragonDropSkull;
    public final ForgeConfigSpec.BooleanValue dragonDropHeart;
    public final ForgeConfigSpec.BooleanValue dragonDropBlood;
    public final ForgeConfigSpec.IntValue dragonTargetSearchLength;
    public final ForgeConfigSpec.IntValue dragonWanderFromHomeDistance;
    public final ForgeConfigSpec.IntValue dragonHungerTickRate;
    public final ForgeConfigSpec.BooleanValue villagersFearDragons;
    public final ForgeConfigSpec.BooleanValue animalsFearDragons;
    public final ForgeConfigSpec.DoubleValue dragonsteelBaseDamage;
    public final ForgeConfigSpec.IntValue dragonsteelBaseArmor;
    public final ForgeConfigSpec.DoubleValue dragonsteelBaseArmorToughness;
    public final ForgeConfigSpec.IntValue dragonsteelBaseDurability;
    public final ForgeConfigSpec.IntValue dragonsteelBaseDurabilityEquipment;
    public final ForgeConfigSpec.BooleanValue dragonMovedWronglyFix;
    public final ForgeConfigSpec.DoubleValue dragonBlockBreakingDropChance;
    public final ForgeConfigSpec.BooleanValue completeDragonPathfinding; // FIXME :: Unused
    public final ForgeConfigSpec.BooleanValue explosiveDragonBreath;
    public final ForgeConfigSpec.BooleanValue chunkLoadSummonCrystal;
    public ForgeConfigSpec.IntValue dangerousWorldGenDistanceLimit;
    public ForgeConfigSpec.IntValue dangerousWorldGenSeparationLimit;
    public final ForgeConfigSpec.DoubleValue dragonFlightSpeedMod;
    public ForgeConfigSpec.IntValue dragonPathfindingThreads;
    public ForgeConfigSpec.IntValue maxDragonPathingNodes;
    public ForgeConfigSpec.BooleanValue pathfindingDebug;
    public final ForgeConfigSpec.BooleanValue dragonWeaponFireAbility;
    public final ForgeConfigSpec.BooleanValue dragonWeaponIceAbility;
    public final ForgeConfigSpec.BooleanValue dragonWeaponLightningAbility;
    public ForgeConfigSpec.IntValue villagerHouseWeight;
    public ForgeConfigSpec.BooleanValue allowAttributeOverriding;

    public ServerConfig(final ForgeConfigSpec.Builder builder) {
        builder.push("Generation");
        builder.push("Dragon");
        this.generateDragonSkeletons = buildBoolean(builder, "Generate Dragon Skeletons", "all", true, "Whether to generate dragon skeletons or not");
        this.generateDragonSkeletonChance = buildInt(builder, "Generate Dragon Skeleton Chance", "all", 300, 1, 10000, "1 out of this number chance per chunk for generation");
        this.generateDragonDenChance = buildInt(builder, "Generate Dragon Cave Chance", "all", 180, 1, 10000, "1 out of this number chance per chunk for generation");
        this.generateDragonRoostChance = buildInt(builder, "Generate Dragon Roost Chance", "all", 360, 1, 10000, "1 out of this number chance per chunk for generation");
        this.dragonDenGoldAmount = buildInt(builder, "Dragon Den Gold Amount", "all", 4, 1, 10000, "1 out of this number chance per block that gold will generate in dragon lairs.");
        this.oreToStoneRatioForDragonCaves = buildInt(builder, "Dragon Cave Ore Ratio", "all", 45, 1, 10000, "Ratio of Stone(this number) to Ores in Dragon Caves");
        builder.pop();
        builder.push("Structures-Features");
        this.dangerousWorldGenDistanceLimit = buildInt(builder, "Dangerous World Gen Dist From Spawn", "all", 800, 1, 10000, "How far away dangerous structures(dragon roosts, etc.) must be from spawn.");
        this.dangerousWorldGenSeparationLimit = buildInt(builder, "Dangerous World Gen Dist Seperation", "all", 300, 1, 10000, "How far away dangerous structures(dragon roosts, etc.) must be from the last generated structure.");

        this.spawnGlaciers = buildBoolean(builder, "Generate Glaciers", "all", true, "Whether to generate glacier biomes or not");
        this.glacierSpawnChance = buildInt(builder, "Glacier Spawn Weight", "all", 4, 1, 10000, "Glacier Spawn Weight. Higher number = more common");





        builder.pop();
        builder.pop();
        builder.push("Dragons");
        builder.push("Griefing");
        this.dragonGriefing = buildBoolean(builder, "Dragon Griefing", "all", false, "Dragon griefing - 2 is no griefing, 1 is breaking weak blocks, 0 is default. This value is also affected by minecrafts mob griefing rule."); //TODO: Redo description
        this.tamedDragonGriefing = buildBoolean(builder, "Tamed Dragon Griefing", "all", true, "True if tamed dragons can follow the griefing rules.");
        this.dragonBlockBreakingDropChance = buildDouble(builder, "Dragon Block Breaking Drop Chance", "all", 0.1F, 0.0F, 1.0F, "The percentage chance for a block to drop as an item when a dragon breaks it.");
        builder.pop();
        builder.push("Attributes");
        this.dragonEggTime = buildInt(builder, "Dragon Egg Hatch Time", "all", 7200, 1, Integer.MAX_VALUE, "How long it takes(in ticks) for a dragon egg to hatch");
        this.dragonFlapNoiseDistance = buildInt(builder, "Dragon Flap Noise Distance", "all", 4, 0, 10000, "Dragon Flap Noise Distance - Larger number, further away you can hear it");
        this.dragonFluteDistance = buildInt(builder, "Dragon Flute Distance", "all", 4, 0, 10000, "Dragon Flute Distance - how many chunks away is the dragon flute effective?");
        this.dragonHealth = buildDouble(builder, "Dragon Health", "all", 500, 1, 100000, "Max dragon health. Health is scaled to this");
        this.dragonAttackDamage = buildInt(builder, "Dragon Attack Damage", "all", 17, 1, 10000, "Max dragon attack damage. Attack Damage is scaled to this");
        this.dragonAttackDamageFire = buildDouble(builder, "Dragon Attack Damage(Fire breath)", "all", 2.0F, 0, 10000, "Damage dealt from a successful fire breath attack. Attack Damage is scaled to by age, so a stage 5 dragon will deal 5x as much as this number");
        this.dragonAttackDamageIce = buildDouble(builder, "Dragon Attack Damage(Ice breath)", "all", 2.5F, 0, 10000, "Damage dealt from a successful ice breath attack. Attack Damage is scaled to by age, so a stage 5 dragon will deal 5x as much as this number");
        this.dragonAttackDamageLightning = buildDouble(builder, "Dragon Attack Damage(Lightning breath)", "all", 3.5F, 0, 10000, "Damage dealt from a successful lightning breath attack. Attack Damage is scaled to by age, so a stage 5 dragon will deal 5x as much as this number");
        this.dragonFlightSpeedMod = buildDouble(builder, "Dragon Flight Speed Modifier", "all", 1F, 0.0F, 2.0F, "Change this to slow down or speed up dragon.");
        this.dragonMovedWronglyFix = buildBoolean(builder, "Dragon Moved Wrongly Error Fix", "all", false, "Enable this if your server is being bombarded with moved wrongly or moved too fast console messages. REQUIRES RESTART!");
        builder.pop();
        builder.push("Behaviour");
        this.maxDragonFlight = buildInt(builder, "Max Dragon Flight Height", "all", 256, 100, Integer.MAX_VALUE, "How high dragons can fly, in Y height.");
        this.dragonGoldSearchLength = buildInt(builder, "Dragon Gold Search Length", "all", 30, 0, 10000, "How far away dragons will detect gold blocks being destroyed or chests being opened");
        this.canDragonsHealFromBiting = buildBoolean(builder, "Dragon Bite Heal", "all", true, "Whether the bite attack from a dragon heals them or not.");
        this.canDragonsDespawn = buildBoolean(builder, "Dragons Despawn", "all", true, "True if dragons can despawn. Note that if this is false there may be SERIOUS lag issues.");
        this.doDragonsSleep = buildBoolean(builder, "Tamed Dragons Sleep", "all", true, "True if tamed dragons go to sleep at night.");
        this.dragonDigWhenStuck = buildBoolean(builder, "Dragons Dig When Stuck", "all", true, "True if dragons can break blocks if they get stuck. Turn this off if your dragons randomly explode.");
        this.dragonDropSkull = buildBoolean(builder, "Dragons Drop Skull", "all", true, "True if dragons can drop their skull on death.");
        this.dragonDropHeart = buildBoolean(builder, "Dragons Drop Heart", "all", true, "True if dragons can drop their heart on death.");
        this.dragonDropBlood = buildBoolean(builder, "Dragons Drop Blood", "all", true, "True if dragons can drop their blood on death.");
        this.explosiveDragonBreath = buildBoolean(builder, "Explosive Dragon Breath", "all", false, "True if dragons fire/ice charges create secondary explosions that launch blocks everywhere. Turn this to true if you like unfair explosions. Or lag.");
        this.dragonTargetSearchLength = buildInt(builder, "Dragon Target Search Length", "all", 128, 1, 10000, "How many blocks away can dragons spot potential prey. Note that increasing this could cause lag.");
        this.dragonWanderFromHomeDistance = buildInt(builder, "Dragon Wander From Home Distance", "all", 40, 1, 10000, "How many blocks away can dragons wander from their defined \"home\" position.");
        this.dragonHungerTickRate = buildInt(builder, "Dragon Hunger Tick Rate", "all", 3000, 1, 10000, "Every interval of this number in ticks, dragon hunger decreases.");
        this.dragonBreakBlockCooldown = buildInt(builder, "Dragon Block Break Cooldown", "all", 5, 0, 10000, "Every interval of this number in ticks, dragon allowed to break blocks.");
        this.villagersFearDragons = buildBoolean(builder, "Villagers Fear Dragons", "all", true, "True if villagers should run away and hide from dragons and other hostile Ice and Fire mobs.");
        this.animalsFearDragons = buildBoolean(builder, "Animals Fear Dragons", "all", true, "True if animals should run away and hide from dragons and other hostile Ice and Fire mobs.");
        this.completeDragonPathfinding = buildBoolean(builder, "Intelligent Dragon Pathfinding", "all", false, "A more intelligent dragon pathfinding system, but is also laggier. Turn this on if you think dragons are too stupid.");
        builder.pop();
        builder.pop();
        builder.push("Mobs");
        builder.push("Others");

        this.villagerHouseWeight = buildInt(builder, "Villager Scribe House Weight", "all", 5, 0, 10000, "Villager Scribe House generation weight. Lower = lower chance to spawn");

        this.allowAttributeOverriding = buildBoolean(builder, "Allow Attribute Overriding", "all", true, "Allows attributes for mobs to be overridden via the config file. One might want to disable this if other mods are enabled that change mob attributes e.g armor, health etc...");
        builder.pop();
        builder.pop();
        builder.push("Items");
        this.dragonsteelBaseDamage = buildDouble(builder, "Dragonsteel Sword Base Attack Strength", "all", 25, 5, Integer.MAX_VALUE, "Default attack strength of a dragonsteel sword.");
        this.dragonsteelBaseArmor = buildInt(builder, "Dragonsteel Base Armor", "all", 12, 7, Integer.MAX_VALUE, "Default armor value of dragonsteel chestplate.");
        this.dragonsteelBaseArmorToughness = buildDouble(builder, "Dragonsteel Base Armor Toughness", "all", 6, 0, Double.MAX_VALUE, "Default armor toughness value of dragonsteel.");
        this.dragonsteelBaseDurability = buildInt(builder, "Dragonsteel Base Durability", "all", 8000, 1, Integer.MAX_VALUE, "Default durability value of dragonsteel tools.");
        this.dragonsteelBaseDurabilityEquipment = buildInt(builder, "Dragonsteel Base Durability Equipment", "all", 8000, 1, Integer.MAX_VALUE, "Default durability value of dragonsteel equipment.");
        this.chunkLoadSummonCrystal = buildBoolean(builder, "Chunk Load Summon Crystal", "all", true, "True if the summon crystal can load chunks to find dragons.");
        this.dragonWeaponFireAbility = buildBoolean(builder, "Dragon Bone Fire Abilities", "all", true, "True if the dragon bone fire sword ignites attackers.");
        this.dragonWeaponIceAbility = buildBoolean(builder, "Dragon Bone Ice Abilities", "all", true, "True if the dragon bone ice sword freezes attackers.");
        this.dragonWeaponLightningAbility = buildBoolean(builder, "Dragon Bone Lightning Abilities", "all", true, "True if the dragon bone lightning sword strikes attackers.");
        builder.pop();
        builder.push("Pathfinding");
        this.dragonPathfindingThreads = buildInt(builder, "Dragon Pathfinding Threads", "all", 3, 1, Integer.MAX_VALUE, "Maximum threads to use for dragon pathfinding. Increase this number if pathing is slow and you have many cores.");
        this.maxDragonPathingNodes = buildInt(builder, "Dragon Max Pathfinding Nodes", "all", 5000, 1, Integer.MAX_VALUE, "Maximum nodes for dragons to path too. Decrease this is dragon pathfinding is super slow or intensive.");
        this.pathfindingDebug = buildBoolean(builder, "Debug Pathfinding Mode", "all", false, "Enables the option to draw the pathfinding nodes when enabled (use a stick on an entity)");
    }

    private static ForgeConfigSpec.BooleanValue buildBoolean(ForgeConfigSpec.Builder builder, String name, String catagory, boolean defaultValue, String comment){
        return builder.comment(comment).translation(name).define(name, defaultValue);
    }

    private static ForgeConfigSpec.IntValue buildInt(ForgeConfigSpec.Builder builder, String name, String catagory, int defaultValue, int min, int max, String comment){
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }

    private static ForgeConfigSpec.DoubleValue buildDouble(ForgeConfigSpec.Builder builder, String name, String catagory, double defaultValue, double min, double max, String comment){
        return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
    }
}
