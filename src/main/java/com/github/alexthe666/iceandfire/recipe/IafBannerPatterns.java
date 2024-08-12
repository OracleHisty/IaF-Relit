package com.github.alexthe666.iceandfire.recipe;

import com.github.alexthe666.iceandfire.IceAndFire;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class IafBannerPatterns {
    public static final DeferredRegister<BannerPattern> BANNERS = DeferredRegister.create(Registries.BANNER_PATTERN, IceAndFire.MODID);
    public static final RegistryObject<BannerPattern> PATTERN_FIRE = BANNERS.register("fire", () -> new BannerPattern("iaf_fire"));
    public static final RegistryObject<BannerPattern> PATTERN_ICE = BANNERS.register("ice", () -> new BannerPattern("iaf_ice"));
    public static final RegistryObject<BannerPattern> PATTERN_LIGHTNING = BANNERS.register("lightning", () -> new BannerPattern("iaf_lightning"));
    public static final RegistryObject<BannerPattern> PATTERN_FIRE_HEAD = BANNERS.register("fire_head", () -> new BannerPattern("iaf_fire_head"));
    public static final RegistryObject<BannerPattern> PATTERN_ICE_HEAD = BANNERS.register("ice_head", () -> new BannerPattern("iaf_ice_head"));
    public static final RegistryObject<BannerPattern> PATTERN_LIGHTNING_HEAD = BANNERS.register("lightning_head", () -> new BannerPattern("iaf_lightning_head"));

}
