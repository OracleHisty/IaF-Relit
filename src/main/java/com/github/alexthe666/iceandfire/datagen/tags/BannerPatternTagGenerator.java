package com.github.alexthe666.iceandfire.datagen.tags;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.recipe.IafBannerPatterns;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class BannerPatternTagGenerator extends TagsProvider<BannerPattern> {
    public static final TagKey<BannerPattern> FIRE_BANNER_PATTERN = create("pattern_item/fire");
    public static final TagKey<BannerPattern> ICE_BANNER_PATTERN = create("pattern_item/ice");
    public static final TagKey<BannerPattern> LIGHTNING_BANNER_PATTERN = create("pattern_item/lightning");
    public static final TagKey<BannerPattern> FIRE_HEAD_BANNER_PATTERN = create("pattern_item/fire_head");
    public static final TagKey<BannerPattern> ICE_HEAD_BANNER_PATTERN = create("pattern_item/ice_head");
    public static final TagKey<BannerPattern> LIGHTNING_HEAD_BANNER_PATTERN = create("pattern_item/lightning_head");

    public BannerPatternTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, Registries.BANNER_PATTERN, provider, IceAndFire.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(FIRE_BANNER_PATTERN).add(IafBannerPatterns.PATTERN_FIRE.getKey());
        this.tag(ICE_BANNER_PATTERN).add(IafBannerPatterns.PATTERN_ICE.getKey());
        this.tag(LIGHTNING_BANNER_PATTERN).add(IafBannerPatterns.PATTERN_LIGHTNING.getKey());
        this.tag(FIRE_HEAD_BANNER_PATTERN).add(IafBannerPatterns.PATTERN_FIRE_HEAD.getKey());
        this.tag(ICE_HEAD_BANNER_PATTERN).add(IafBannerPatterns.PATTERN_ICE_HEAD.getKey());
        this.tag(LIGHTNING_HEAD_BANNER_PATTERN).add(IafBannerPatterns.PATTERN_LIGHTNING_HEAD.getKey());
    }

    private static TagKey<BannerPattern> create(String name) {
        return TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(IceAndFire.MODID, name));
    }

    @Override
    public String getName() {
        return "Ice and Fire Banner Pattern Tags";
    }
}

