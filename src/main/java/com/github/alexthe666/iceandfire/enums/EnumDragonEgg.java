package com.github.alexthe666.iceandfire.enums;

import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public enum EnumDragonEgg implements StringRepresentable {
    RED(ChatFormatting.DARK_RED, DragonType.FIRE, () -> IafItemRegistry.DRAGONEGG_RED, () -> IafItemRegistry.DRAGONSCALES_RED),
    GREEN(ChatFormatting.DARK_GREEN, DragonType.FIRE, () -> IafItemRegistry.DRAGONEGG_GREEN, () -> IafItemRegistry.DRAGONSCALES_GREEN),
    BRONZE(ChatFormatting.GOLD, DragonType.FIRE, () -> IafItemRegistry.DRAGONEGG_BRONZE, () -> IafItemRegistry.DRAGONSCALES_BRONZE),
    GRAY(ChatFormatting.GRAY, DragonType.FIRE, () -> IafItemRegistry.DRAGONEGG_GRAY, () -> IafItemRegistry.DRAGONSCALES_GRAY),
    BLUE(ChatFormatting.AQUA, DragonType.ICE, () -> IafItemRegistry.DRAGONEGG_BLUE, () -> IafItemRegistry.DRAGONSCALES_BLUE),
    WHITE(ChatFormatting.WHITE, DragonType.ICE, () -> IafItemRegistry.DRAGONEGG_WHITE, () -> IafItemRegistry.DRAGONSCALES_WHITE),
    SAPPHIRE(ChatFormatting.BLUE, DragonType.ICE, () -> IafItemRegistry.DRAGONEGG_SAPPHIRE, () -> IafItemRegistry.DRAGONSCALES_SAPPHIRE),
    SILVER(ChatFormatting.DARK_GRAY, DragonType.ICE, () -> IafItemRegistry.DRAGONEGG_SILVER, () -> IafItemRegistry.DRAGONSCALES_SILVER),
    ELECTRIC(ChatFormatting.DARK_BLUE, DragonType.LIGHTNING, () -> IafItemRegistry.DRAGONEGG_ELECTRIC, () -> IafItemRegistry.DRAGONSCALES_ELECTRIC),
    AMYTHEST(ChatFormatting.LIGHT_PURPLE, DragonType.LIGHTNING, () -> IafItemRegistry.DRAGONEGG_AMYTHEST, () -> IafItemRegistry.DRAGONSCALES_AMYTHEST),
    COPPER(ChatFormatting.GOLD, DragonType.LIGHTNING, () -> IafItemRegistry.DRAGONEGG_COPPER, () -> IafItemRegistry.DRAGONSCALES_COPPER),
    BLACK(ChatFormatting.DARK_GRAY, DragonType.LIGHTNING, () -> IafItemRegistry.DRAGONEGG_BLACK, () -> IafItemRegistry.DRAGONSCALES_BLACK);

    public final ChatFormatting color;
    public final DragonType dragonType;
    private final Supplier<Supplier<Item>> eggItem;
    private final Supplier<Supplier<Item>> scaleItem;


    EnumDragonEgg(final ChatFormatting color, final DragonType dragonType, Supplier<Supplier<Item>> eggItem, Supplier<Supplier<Item>> scaleItem) {
        this.color = color;
        this.dragonType = dragonType;
        this.eggItem = eggItem;
        this.scaleItem = scaleItem;
    }

    public Item getEggItem() {
        return eggItem.get().get();
    }

    public Item getScaleItem() {
        return scaleItem.get().get();
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}
