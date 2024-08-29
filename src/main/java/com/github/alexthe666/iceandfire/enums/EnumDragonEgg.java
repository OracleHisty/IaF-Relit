package com.github.alexthe666.iceandfire.enums;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Supplier;

public enum EnumDragonEgg implements StringRepresentable {
    RED(0x000000, new DragonTextures("red"), () -> DragonType.FIRE, () -> IafItemRegistry.DRAGONEGG_RED, () -> IafItemRegistry.DRAGONSCALES_RED, () -> IafBlockRegistry.DRAGON_SCALE_RED),
    GREEN(0xaa0000, new DragonTextures("green"), () -> DragonType.FIRE, () -> IafItemRegistry.DRAGONEGG_GREEN, () -> IafItemRegistry.DRAGONSCALES_GREEN, () -> IafBlockRegistry.DRAGON_SCALE_GREEN),
    BRONZE(0xaa0000, new DragonTextures("bronze"), () -> DragonType.FIRE, () -> IafItemRegistry.DRAGONEGG_BRONZE, () -> IafItemRegistry.DRAGONSCALES_BRONZE, () -> IafBlockRegistry.DRAGON_SCALE_BRONZE),
    GRAY(0xaaaa00, new DragonTextures("gray"), () -> DragonType.FIRE, () -> IafItemRegistry.DRAGONEGG_GRAY, () -> IafItemRegistry.DRAGONSCALES_GRAY, () -> IafBlockRegistry.DRAGON_SCALE_GRAY),
    BLUE(0xaa0000, new DragonTextures("blue"), () -> DragonType.ICE, () -> IafItemRegistry.DRAGONEGG_BLUE, () -> IafItemRegistry.DRAGONSCALES_BLUE, () -> IafBlockRegistry.DRAGON_SCALE_BLUE),
    WHITE(0xaa00aa, new DragonTextures("white"), () -> DragonType.ICE, () -> IafItemRegistry.DRAGONEGG_WHITE, () -> IafItemRegistry.DRAGONSCALES_WHITE, () -> IafBlockRegistry.DRAGON_SCALE_WHITE),
    SAPPHIRE(0xffaa00, new DragonTextures("sapphire"), () -> DragonType.ICE, () -> IafItemRegistry.DRAGONEGG_SAPPHIRE, () -> IafItemRegistry.DRAGONSCALES_SAPPHIRE, () -> IafBlockRegistry.DRAGON_SCALE_SAPPHIRE),
    SILVER(0xaaaaaa, new DragonTextures("silver"), () -> DragonType.ICE, () -> IafItemRegistry.DRAGONEGG_SILVER, () -> IafItemRegistry.DRAGONSCALES_SILVER, () -> IafBlockRegistry.DRAGON_SCALE_SILVER),
    ELECTRIC(0x555555, new DragonTextures("electric"), () -> DragonType.LIGHTNING, () -> IafItemRegistry.DRAGONEGG_ELECTRIC, () -> IafItemRegistry.DRAGONSCALES_ELECTRIC, () -> IafBlockRegistry.DRAGON_SCALE_ELECTRIC),
    AMYTHEST(0x5555ff, new DragonTextures("amythest"), () -> DragonType.LIGHTNING, () -> IafItemRegistry.DRAGONEGG_AMYTHEST, () -> IafItemRegistry.DRAGONSCALES_AMYTHEST, () -> IafBlockRegistry.DRAGON_SCALE_AMYTHEST),
    COPPER(0x55ff55, new DragonTextures("copper"), () -> DragonType.LIGHTNING, () -> IafItemRegistry.DRAGONEGG_COPPER, () -> IafItemRegistry.DRAGONSCALES_COPPER, () -> IafBlockRegistry.DRAGON_SCALE_COPPER),
    BLACK(0x55ffff, new DragonTextures("black"), () -> DragonType.LIGHTNING, () -> IafItemRegistry.DRAGONEGG_BLACK, () -> IafItemRegistry.DRAGONSCALES_BLACK, () -> IafBlockRegistry.DRAGON_SCALE_BLACK);

    public final int color;
    public final Supplier<DragonType> dragonType;
    private final Supplier<Supplier<Item>> eggItem;
    private final Supplier<Supplier<Item>> scaleItem;
    private final DragonTextures textures;


    EnumDragonEgg(final int color, DragonTextures textures, Supplier<DragonType> dragonType, Supplier<Supplier<Item>> eggItem, Supplier<Supplier<Item>> scaleItem, Supplier<Supplier<Block>> scaleBlock) {
        this.color = color;
        this.dragonType = dragonType;
        this.eggItem = eggItem;
        this.scaleItem = scaleItem;
        this.textures = textures;
    }

    public Item getEggItem() {
        return eggItem.get().get();
    }

    public Item getScaleItem() {
        return scaleItem.get().get();
    }

    public DragonType dragonType() {
        return dragonType.get();
    }

    @Override
    public @NotNull String getSerializedName() {
        return name().toLowerCase();
    }

    public Component getTextComponent() {
        return Component.translatable("dragon." + toString().toLowerCase()).withStyle(Style.EMPTY.withColor(color));
    }

    public DragonTextures getTextures() {
        return textures;
    }

    public record DragonTextures(Map<DragonType.DragonLifeStages, ResourceLocation> regular, Map<DragonType.DragonLifeStages, ResourceLocation> sleeping, Map<DragonType.DragonLifeStages, ResourceLocation> eyes, ResourceLocation egg, ResourceLocation maleOverlay) {
        public DragonTextures(String name) {
            this(Map.of(
                    DragonType.DragonLifeStages.HATCHLING, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/regular_1.png"),
                    DragonType.DragonLifeStages.CHILD, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/regular_2.png"),
                    DragonType.DragonLifeStages.TEEN, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/regular_3.png"),
                    DragonType.DragonLifeStages.ADULT, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/regular_4.png"),
                    DragonType.DragonLifeStages.ELDER, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/regular_5.png")
            ), Map.of(
                    DragonType.DragonLifeStages.HATCHLING, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/eyes_1.png"),
                    DragonType.DragonLifeStages.CHILD, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/eyes_2.png"),
                    DragonType.DragonLifeStages.TEEN, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/eyes_3.png"),
                    DragonType.DragonLifeStages.ADULT, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/eyes_4.png"),
                    DragonType.DragonLifeStages.ELDER, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/eyes_5.png")
            ), Map.of(
                    DragonType.DragonLifeStages.HATCHLING, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/sleeping_1.png"),
                    DragonType.DragonLifeStages.CHILD, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/sleeping_2.png"),
                    DragonType.DragonLifeStages.TEEN, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/sleeping_3.png"),
                    DragonType.DragonLifeStages.ADULT, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/sleeping_4.png"),
                    DragonType.DragonLifeStages.ELDER, new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/sleeping_5.png")
            ), new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/egg.png"), new ResourceLocation("iceandfire:textures/models/scales/" + name +  "/male_overlay.png"));
        }
    }
}
