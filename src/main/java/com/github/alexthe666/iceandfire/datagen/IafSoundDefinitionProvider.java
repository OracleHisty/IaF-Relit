package com.github.alexthe666.iceandfire.datagen;

import com.github.alexthe666.citadel.repack.jcodec.algo.SoundFilter;
import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.misc.IafSoundRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SoundDefinition;
import net.minecraftforge.common.data.SoundDefinitionsProvider;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IafSoundDefinitionProvider extends SoundDefinitionsProvider {
    private final ExistingFileHelper helper;

    private final ExistingFileHelper.ResourceType SOUND = new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".ogg", "sound");


    public IafSoundDefinitionProvider(PackOutput output, String modid, ExistingFileHelper helper) {
        super(output, modid, helper);
        this.helper = helper;
    }
    @Override
    public void registerSounds() {
        this.add(IafSoundRegistry.BESTIARY_PAGE, 2);
        this.add(IafSoundRegistry.EGG_HATCH);
        this.add(IafSoundRegistry.DRAGONFLUTE);
        this.add(IafSoundRegistry.GOLD_PILE_STEP);
        this.add(IafSoundRegistry.GOLD_PILE_BREAK);
        this.add(IafSoundRegistry.DRAGON_FLIGHT, 4);

        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 10);
        /*this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);
        this.add(IafSoundRegistry.FIRE, DragonType.DragonLifeStages.HATCHLING, DragonType.DragonSoundType.DEATH, 1);*/


    }

    public void add(IafSoundRegistry.DragonSoundGroup group, DragonType.DragonLifeStages stage, DragonType.DragonSoundType sound, int instances) {
        Stream.of(group.lifeStages().values()).flatMap(a -> a.stream()).flatMap(a -> a.values().stream()).forEach(soundEvent -> add(soundEvent, instances));
        add(group.breath());
    }

    public void add(SoundEvent soundEvent) {
    add(soundEvent, 0);
    }

    private void add(SoundEvent soundEvent, int numberOfInstnaces) {
        var soundDefinition = SoundDefinition.definition();

        var sound = new ResourceLocation(soundEvent.getLocation().toString().replace(".", "/"));

        System.out.println(sound.withSuffix(".ogg") + ": " + helper.exists(sound, SOUND));

        if(numberOfInstnaces == 0) {
            sound = new ResourceLocation(soundEvent.getLocation().toString().replace(".", "/"));

            soundDefinition.with(SoundDefinition.Sound.sound(sound, SoundDefinition.SoundType.EVENT));
        } else for (int i = 0; i < numberOfInstnaces; i++) {
            sound = new ResourceLocation(soundEvent.getLocation().toString().replace(".", "/") + "_" + i);
            soundDefinition.with(SoundDefinition.Sound.sound(sound, SoundDefinition.SoundType.EVENT).volume(0.4));
        }


        this.add(soundEvent, soundDefinition);
    }
}
