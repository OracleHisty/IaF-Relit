package com.github.alexthe666.iceandfire.datagen;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.datagen.tags.*;
import com.github.alexthe666.iceandfire.misc.IafSoundRegistry;
import net.minecraft.DetectedVersion;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.data.*;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


@Mod.EventBusSubscriber(modid = IceAndFire.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();
        DatapackBuiltinEntriesProvider datapackProvider = new RegistryDataGenerator(output, provider);
        CompletableFuture<HolderLookup.Provider> lookupProvider = datapackProvider.getRegistryProvider();
        generator.addProvider(event.includeServer(), datapackProvider);
        generator.addProvider(event.includeServer(), new BannerPatternTagGenerator(output, provider, helper));
        generator.addProvider(event.includeServer(), new POITagGenerator(output, provider, helper));
        generator.addProvider(true, new PackMetadataGenerator(output).add(PackMetadataSection.TYPE, new PackMetadataSection(
                Component.literal("Resources for Ice and Fire"),
                DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
                Arrays.stream(PackType.values()).collect(Collectors.toMap(Function.identity(), DetectedVersion.BUILT_IN::getPackVersion)))));
        BlockTagsProvider blocktags  = new IafBlockTags(output, provider, helper);
        generator.addProvider(event.includeServer(), blocktags);
        generator.addProvider(event.includeServer(), new IafItemTags(output, provider, blocktags.contentsGetter(), helper));
        generator.addProvider(event.includeServer(), new IafRecipes(output));
//        generator.addProvider(event.includeClient(), new IafSoundDefinitionProvider(output, IceAndFire.MODID, helper));
    }
}
