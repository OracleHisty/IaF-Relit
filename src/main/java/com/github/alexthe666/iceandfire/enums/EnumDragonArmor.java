package com.github.alexthe666.iceandfire.enums;

import com.github.alexthe666.citadel.server.item.CustomArmorMaterial;
import com.github.alexthe666.iceandfire.item.IafArmorMaterial;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.alexthe666.iceandfire.item.ItemScaleArmor;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public enum EnumDragonArmor {

    armor_red(12, EnumDragonEgg.RED, () -> IafItemRegistry.DRAGONSCALES_RED),
    armor_bronze(13, EnumDragonEgg.BRONZE, () -> IafItemRegistry.DRAGONSCALES_BRONZE),
    armor_green(14, EnumDragonEgg.GREEN, () -> IafItemRegistry.DRAGONSCALES_GREEN),
    armor_gray(15, EnumDragonEgg.GRAY, () -> IafItemRegistry.DRAGONSCALES_GRAY),
    armor_blue(12, EnumDragonEgg.BLUE, () -> IafItemRegistry.DRAGONSCALES_BLUE),
    armor_white(13, EnumDragonEgg.WHITE, () -> IafItemRegistry.DRAGONSCALES_WHITE),
    armor_sapphire(14, EnumDragonEgg.SAPPHIRE, () -> IafItemRegistry.DRAGONSCALES_SAPPHIRE),
    armor_silver(15, EnumDragonEgg.SILVER, () -> IafItemRegistry.DRAGONSCALES_SILVER),
    armor_electric(12, EnumDragonEgg.ELECTRIC, () -> IafItemRegistry.DRAGONSCALES_ELECTRIC),
    armor_amythest(13, EnumDragonEgg.AMYTHEST, () -> IafItemRegistry.DRAGONSCALES_AMYTHEST),
    armor_copper(14, EnumDragonEgg.COPPER, () -> IafItemRegistry.DRAGONSCALES_COPPER),
    armor_black(15, EnumDragonEgg.BLACK, () -> IafItemRegistry.DRAGONSCALES_BLACK);

    public CustomArmorMaterial material;
    public int armorId;
    public EnumDragonEgg eggType;
    public final Supplier<Supplier<Item>> itemSupplier;
    public RegistryObject<Item> helmet;
    public RegistryObject<Item> chestplate;
    public RegistryObject<Item> leggings;
    public RegistryObject<Item> boots;
    public CustomArmorMaterial armorMaterial;

    EnumDragonArmor(int armorId, EnumDragonEgg eggType, Supplier<Supplier<Item>> itemSupplier) {
        this.armorId = armorId;
        this.eggType = eggType;
        this.itemSupplier = itemSupplier;
    }

    public static void initArmors() {
        for (EnumDragonArmor armor : EnumDragonArmor.values()) {

            armor.armorMaterial = new IafArmorMaterial("iceandfire:armor_dragon_scales" + (armor.ordinal() + 1), 36, new int[]{5, 7, 9, 5}, 15, SoundEvents.ARMOR_EQUIP_CHAIN, 2);
            String sub = armor.name();

            armor.helmet = IafItemRegistry.registerItem(sub + "_helmet", () -> new ItemScaleArmor(armor.eggType, armor, armor.armorMaterial, ArmorItem.Type.HELMET));
            armor.chestplate = IafItemRegistry.registerItem(sub + "_chestplate", () -> new ItemScaleArmor(armor.eggType, armor, armor.armorMaterial, ArmorItem.Type.CHESTPLATE));
            armor.leggings = IafItemRegistry.registerItem(sub + "_leggings", () -> new ItemScaleArmor(armor.eggType, armor, armor.armorMaterial, ArmorItem.Type.LEGGINGS));
            armor.boots = IafItemRegistry.registerItem(sub + "_boots", () -> new ItemScaleArmor(armor.eggType, armor, armor.armorMaterial, ArmorItem.Type.BOOTS));
        }
    }
}
