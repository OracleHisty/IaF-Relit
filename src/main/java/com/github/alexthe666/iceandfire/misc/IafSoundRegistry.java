package com.github.alexthe666.iceandfire.misc;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.DragonType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.NewRegistryEvent;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.github.alexthe666.iceandfire.IceAndFire.MODID;
import static com.github.alexthe666.iceandfire.entity.DragonType.*;

@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = IceAndFire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class IafSoundRegistry {

    public static final SoundEvent BESTIARY_PAGE = createSoundEvent("bestiary_page");

    public static final SoundEvent EGG_HATCH = createSoundEvent("egg_hatch");

    public static final SoundEvent FIREDRAGON_BREATH = createSoundEvent("firedragon_breath");

    public static final SoundEvent ICEDRAGON_BREATH = createSoundEvent("icedragon_breath");

    public static final SoundEvent DRAGONFLUTE = createSoundEvent("dragonflute");
    

    public static final SoundEvent GOLD_PILE_STEP = createSoundEvent("gold_pile_step");

    public static final SoundEvent GOLD_PILE_BREAK = createSoundEvent("gold_pile_break");

    public static final SoundEvent DRAGON_FLIGHT = createSoundEvent("dragon_flight");

    public static Map<DragonLifeStages, Map<DragonSoundType, SoundEvent>> FIRE = registerDragonSoundGroup("fire");
    public static Map<DragonLifeStages, Map<DragonSoundType, SoundEvent>> ICE = registerDragonSoundGroup("ice");
    public static Map<DragonLifeStages, Map<DragonSoundType, SoundEvent>> LIGHTNING = registerDragonSoundGroup("lightning");

    public static Map<DragonLifeStages, Map<DragonSoundType, SoundEvent>> GENERIC = registerDragonSoundGroup("generic");

    public static SoundEvent createDragonSound(String dragonType, DragonLifeStages dragonStage, DragonSoundType soundType) {
        return createSoundEvent((dragonType + "dragon_" + dragonStage + "_" + soundType).toLowerCase());
    }
    public static Map<DragonLifeStages, Map<DragonSoundType, SoundEvent>> registerDragonSoundGroup(String dragonType) {
        var map = new HashMap<DragonLifeStages, Map<DragonSoundType, SoundEvent>>();

        for (var stage:DragonLifeStages.values()) {
            var LifeStageMap = map.putIfAbsent(stage, new HashMap<>());
            for (var soundType:DragonSoundType.values()) {
                LifeStageMap.putIfAbsent(soundType, createDragonSound(dragonType, stage, soundType));
            }
        }

        return map;
    }





    public static final SoundEvent LIGHTNINGDRAGON_BREATH = createSoundEvent("lightningdragon_breath");

    public static final SoundEvent LIGHTNINGDRAGON_BREATH_CRACKLE = createSoundEvent("lightningdragon_breath_crackle");
    private static SoundEvent createSoundEvent(final String soundName) {
        final ResourceLocation soundID = new ResourceLocation(MODID, soundName);
        return SoundEvent.createVariableRangeEvent(soundID);
    }

    @SubscribeEvent
    public static void registerSoundEvents(final NewRegistryEvent event) {
        try {
            for (Field f : IafSoundRegistry.class.getFields()) {
                Object obj = f.get(null);
                if (obj instanceof SoundEvent) {
                    ForgeRegistries.SOUND_EVENTS.register(((SoundEvent) obj).getLocation(), (SoundEvent) obj);
                } else if (obj instanceof SoundEvent[]) {
                    for (SoundEvent soundEvent : (SoundEvent[]) obj) {
                        ForgeRegistries.SOUND_EVENTS.register(soundEvent.getLocation(), soundEvent);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
