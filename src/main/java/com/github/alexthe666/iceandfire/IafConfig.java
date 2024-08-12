package com.github.alexthe666.iceandfire;

import com.github.alexthe666.iceandfire.config.ConfigHolder;
import net.minecraftforge.fml.config.ModConfig;

public class IafConfig {
    public static boolean customMainMenu = true;
    public static boolean useVanillaFont = false;

    //public static boolean logCascadingWorldGen = false;
    public static boolean generateDragonSkeletons = true;
    public static int generateDragonSkeletonChance = 300;
    public static int generateDragonDenChance = 260;
    public static int generateDragonRoostChance = 480;
    public static int dragonDenGoldAmount = 4;
    public static boolean spawnGlaciers = true;
    public static int glacierSpawnChance = 4;
    public static int oreToStoneRatioForDragonCaves = 45;
    public static int dragonEggTime = 7200;
    public static int dragonGriefing = 0;
    public static boolean tamedDragonGriefing = true;
    public static int dragonFlapNoiseDistance = 4;
    public static int dragonFluteDistance = 8;
    public static double dragonHealth = 500;
    public static int dragonAttackDamage = 17;
    public static double dragonAttackDamageFire = 2F;
    public static double dragonAttackDamageIce = 2.5F;
    public static double dragonAttackDamageLightning = 3.5F;
    public static int maxDragonFlight = 256;
    public static int dragonGoldSearchLength = 30;
    public static boolean canDragonsHealFromBiting;
    public static boolean canDragonsDespawn = true;
    public static boolean doDragonsSleep = true;
    public static boolean dragonDigWhenStuck = true;
    public static int dragonBreakBlockCooldown = 5;
    public static boolean dragonDropSkull = true;
    public static boolean dragonDropHeart = true;
    public static boolean dragonDropBlood = true;
    public static int dragonTargetSearchLength = 128;
    public static int dragonWanderFromHomeDistance = 40;
    public static int dragonHungerTickRate = 3000;
    public static double sirenMaxHealth = 50D;
    public static boolean sirenShader = true;
    public static int sirenMaxSingTime = 12000;
    public static int sirenTimeBetweenSongs = 2000;
    public static int generateSirenChance = 400;
    public static int hippocampusSpawnChance = 40;
    public static int stymphalianBirdTargetSearchLength = 48;
    public static double stymphalianBirdBaseAttack = 6D;
    public static int stymphalianBirdFeatherDropChance = 25;
    public static double stymphalianBirdFeatherAttackStength = 1F;
    public static int stymphalianBirdFlockLength = 40;
    public static int stymphalianBirdFlightHeight = 80;
    public static boolean stymphalianBirdsDataTagDrops = true;
    public static boolean stympahlianBirdAttackAnimals = false;
    public static int stymphalianBirdSpawnChance = 80;
    public static boolean villagersFearDragons = true;
    public static boolean animalsFearDragons = true;
    public static boolean spawnAmphitheres = true;
    public static int amphithereSpawnRate = 50;
    public static float amphithereVillagerSearchLength = 48;
    public static int amphithereTameTime = 400;
    public static double amphithereFlightSpeed = 1.75F;
    public static double amphithereMaxHealth = 50D;
    public static double amphithereAttackStrength = 7D;
    public static double dragonsteelBaseDamage = 25F;
    public static int dragonsteelBaseArmor = 12;
    public static float dragonsteelBaseArmorToughness = 6;
    public static int dragonsteelBaseDurability = 8000;
    public static int dragonsteelBaseDurabilityEquipment = 8000;
    public static boolean dragonMovedWronglyFix = false;
    public static boolean weezerTinkers = true;
    public static double dragonBlockBreakingDropChance = 0.1D;
    public static boolean dragonAuto3rdPerson = true;
    public static boolean explosiveDragonBreath = false;
    public static double weezerTinkersDisarmChance = 0.2F;
    public static boolean chunkLoadSummonCrystal = true;
    public static double dangerousWorldGenDistanceLimit = 1000;
    public static double dangerousWorldGenSeparationLimit = 300;
    public static double dragonFlightSpeedMod = 1F;
    public static double hippocampusSwimSpeedMod = 1F;

    public static int dragonPathfindingThreads = 3;
    public static int maxDragonPathingNodes = 5000;
    public static boolean pathfindingDebug = false;
    public static boolean dragonWeaponFireAbility = true;
    public static boolean dragonWeaponIceAbility = true;
    public static boolean dragonWeaponLightningAbility = true;
    public static int villagerHouseWeight = 5;
    public static boolean allowAttributeOverriding = true;

    public static void bakeClient(final ModConfig config) {
        try {
            customMainMenu = ConfigHolder.CLIENT.customMainMenu.get();
            useVanillaFont = ConfigHolder.CLIENT.useVanillaFont.get();
         dragonAuto3rdPerson = ConfigHolder.CLIENT.dragonAuto3rdPerson.get();
        } catch (Exception e) {
            IceAndFire.LOGGER.warn("An exception was caused trying to load the client config for Ice and Fire.");
            e.printStackTrace();
        }
    }

    public static void bakeServer(final ModConfig config) {
        try {
            generateDragonSkeletons = ConfigHolder.SERVER.generateDragonSkeletons.get();
            generateDragonSkeletonChance = ConfigHolder.SERVER.generateDragonSkeletonChance.get();
            generateDragonDenChance = ConfigHolder.SERVER.generateDragonDenChance.get();
            generateDragonRoostChance = ConfigHolder.SERVER.generateDragonRoostChance.get();
            dragonDenGoldAmount = ConfigHolder.SERVER.dragonDenGoldAmount.get();
            spawnGlaciers = ConfigHolder.SERVER.spawnGlaciers.get();
            glacierSpawnChance = ConfigHolder.SERVER.glacierSpawnChance.get();
            oreToStoneRatioForDragonCaves = ConfigHolder.SERVER.oreToStoneRatioForDragonCaves.get();
            dragonEggTime = ConfigHolder.SERVER.dragonEggTime.get();
            dragonGriefing = ConfigHolder.SERVER.dragonGriefing.get();
            tamedDragonGriefing = ConfigHolder.SERVER.tamedDragonGriefing.get();
            dragonFlapNoiseDistance = ConfigHolder.SERVER.dragonFlapNoiseDistance.get();
            dragonFluteDistance = ConfigHolder.SERVER.dragonFluteDistance.get();
            dragonHealth = ConfigHolder.SERVER.dragonHealth.get();
            dragonAttackDamage = ConfigHolder.SERVER.dragonAttackDamage.get();
            dragonAttackDamageFire = ConfigHolder.SERVER.dragonAttackDamageFire.get();
            dragonAttackDamageIce = ConfigHolder.SERVER.dragonAttackDamageIce.get();
            dragonAttackDamageLightning = ConfigHolder.SERVER.dragonAttackDamageLightning.get();
            maxDragonFlight = ConfigHolder.SERVER.maxDragonFlight.get();
            dragonGoldSearchLength = ConfigHolder.SERVER.dragonGoldSearchLength.get();
            canDragonsHealFromBiting = ConfigHolder.SERVER.canDragonsHealFromBiting.get();
            canDragonsDespawn = ConfigHolder.SERVER.canDragonsDespawn.get();
            doDragonsSleep = ConfigHolder.SERVER.doDragonsSleep.get();
            dragonDigWhenStuck = ConfigHolder.SERVER.dragonDigWhenStuck.get();
            dragonBreakBlockCooldown = ConfigHolder.SERVER.dragonBreakBlockCooldown.get();
            dragonDropSkull = ConfigHolder.SERVER.dragonDropSkull.get();
            dragonDropHeart = ConfigHolder.SERVER.dragonDropHeart.get();
            dragonDropBlood = ConfigHolder.SERVER.dragonDropBlood.get();
            dragonTargetSearchLength = ConfigHolder.SERVER.dragonTargetSearchLength.get();
            dragonWanderFromHomeDistance = ConfigHolder.SERVER.dragonWanderFromHomeDistance.get();
            dragonHungerTickRate = ConfigHolder.SERVER.dragonHungerTickRate.get();
            sirenMaxHealth = ConfigHolder.SERVER.sirenMaxHealth.get();
            sirenShader = ConfigHolder.SERVER.sirenShader.get();
            sirenMaxSingTime = ConfigHolder.SERVER.sirenMaxSingTime.get();
            sirenTimeBetweenSongs = ConfigHolder.SERVER.sirenTimeBetweenSongs.get();
            generateSirenChance = ConfigHolder.SERVER.generateSirenChance.get();
            hippocampusSpawnChance = ConfigHolder.SERVER.hippocampusSpawnChance.get();
            stymphalianBirdBaseAttack = ConfigHolder.SERVER.stymphalianBirdBaseAttack.get();
            stymphalianBirdTargetSearchLength = ConfigHolder.SERVER.stymphalianBirdTargetSearchLength.get();
            stymphalianBirdFeatherDropChance = ConfigHolder.SERVER.stymphalianBirdFeatherDropChance.get();
            stymphalianBirdFeatherAttackStength = ConfigHolder.SERVER.stymphalianBirdFeatherAttackStength.get();
            stymphalianBirdFlockLength = ConfigHolder.SERVER.stymphalianBirdFlockLength.get();
            stymphalianBirdFlightHeight = ConfigHolder.SERVER.stymphalianBirdFlightHeight.get();
            stymphalianBirdsDataTagDrops = ConfigHolder.SERVER.stymphalianBirdsDataTagDrops.get();
            stympahlianBirdAttackAnimals = ConfigHolder.SERVER.stympahlianBirdAttackAnimals.get();
            stymphalianBirdSpawnChance = ConfigHolder.SERVER.stymphalianBirdSpawnChance.get();
            villagersFearDragons = ConfigHolder.SERVER.villagersFearDragons.get();
            animalsFearDragons = ConfigHolder.SERVER.animalsFearDragons.get();
            spawnAmphitheres = ConfigHolder.SERVER.spawnAmphitheres.get();
            amphithereSpawnRate = ConfigHolder.SERVER.amphithereSpawnRate.get();
            amphithereVillagerSearchLength = ConfigHolder.SERVER.amphithereVillagerSearchLength.get();
            amphithereTameTime = ConfigHolder.SERVER.amphithereTameTime.get();
            amphithereFlightSpeed = ConfigHolder.SERVER.amphithereFlightSpeed.get();
            amphithereMaxHealth = ConfigHolder.SERVER.amphithereMaxHealth.get();
            amphithereAttackStrength = ConfigHolder.SERVER.amphithereAttackStrength.get();
            dragonsteelBaseDamage = ConfigHolder.SERVER.dragonsteelBaseDamage.get();
            dragonsteelBaseArmor = ConfigHolder.SERVER.dragonsteelBaseArmor.get();
            dragonsteelBaseArmorToughness = ConfigHolder.SERVER.dragonsteelBaseArmorToughness.get().floatValue();
            dragonsteelBaseDurability = ConfigHolder.SERVER.dragonsteelBaseDurability.get();
            dragonsteelBaseDurabilityEquipment = ConfigHolder.SERVER.dragonsteelBaseDurabilityEquipment.get();
            dragonMovedWronglyFix = ConfigHolder.SERVER.dragonMovedWronglyFix.get();
            weezerTinkers = ConfigHolder.SERVER.weezerTinkers.get();
            dragonBlockBreakingDropChance = ConfigHolder.SERVER.dragonBlockBreakingDropChance.get();

            explosiveDragonBreath = ConfigHolder.SERVER.explosiveDragonBreath.get();
            weezerTinkersDisarmChance = ConfigHolder.SERVER.weezerTinkersDisarmChance.get();
            chunkLoadSummonCrystal = ConfigHolder.SERVER.chunkLoadSummonCrystal.get();
            dangerousWorldGenDistanceLimit = ConfigHolder.SERVER.dangerousWorldGenDistanceLimit.get();
            dangerousWorldGenSeparationLimit = ConfigHolder.SERVER.dangerousWorldGenSeparationLimit.get();
            dragonFlightSpeedMod = ConfigHolder.SERVER.dragonFlightSpeedMod.get();
            hippocampusSwimSpeedMod = ConfigHolder.SERVER.hippocampusSwimSpeedMod.get();
            dragonPathfindingThreads = ConfigHolder.SERVER.dragonPathfindingThreads.get();
            maxDragonPathingNodes = ConfigHolder.SERVER.maxDragonPathingNodes.get();
            dragonWeaponFireAbility = ConfigHolder.SERVER.dragonWeaponFireAbility.get();
            dragonWeaponIceAbility = ConfigHolder.SERVER.dragonWeaponIceAbility.get();
            dragonWeaponLightningAbility = ConfigHolder.SERVER.dragonWeaponLightningAbility.get();
            villagerHouseWeight = ConfigHolder.SERVER.villagerHouseWeight.get();
            allowAttributeOverriding = ConfigHolder.SERVER.allowAttributeOverriding.get();
            pathfindingDebug = ConfigHolder.SERVER.pathfindingDebug.get();
        } catch (Exception e) {
            IceAndFire.LOGGER.warn("An exception was caused trying to load the common config for Ice and Fire.");
            e.printStackTrace();
        }
    }

}
