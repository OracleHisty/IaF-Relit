package com.github.alexthe666.iceandfire.item;

import com.github.alexthe666.iceandfire.entity.DragonType;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.github.alexthe666.iceandfire.item.IafItemRegistry.registerItem;

public record DragonItems(RegistryObject<ItemSummoningCrystal> summoningCrystal, RegistryObject<ItemGeneric> blood, RegistryObject<ItemDragonFlesh> flesh, RegistryObject<ItemGeneric> heart, RegistryObject<ItemDragonSkull> skull) {
    private static final Map<DragonType, DragonItems> DRAGON_ITEMS = new HashMap<>();

    public static void of(DragonType type) {
        var summoningCrystal = registerItem("summoning_crystal_%s".formatted(type), () -> new ItemSummoningCrystal(type));
        var blood = registerItem("%s_dragon_blood".formatted(type), ItemGeneric::new);
        var flesh = registerItem("%s_dragon_flesh".formatted(type), () -> new ItemDragonFlesh(type));
        var heart = registerItem("%s_dragon_heart".formatted(type), ItemGeneric::new);
        var skull = registerItem("%s_dragon_heart".formatted(type), () -> new ItemDragonSkull(type));

        var items = new DragonItems(summoningCrystal, blood, flesh, heart, skull);
        DRAGON_ITEMS.put(type, items);
    }

    public static DragonItems getDragonItems(DragonType type) {
        return DRAGON_ITEMS.get(type);
    }

    public static Collection<DragonItems> dragonItems() {
        return DRAGON_ITEMS.values();
    }
}
