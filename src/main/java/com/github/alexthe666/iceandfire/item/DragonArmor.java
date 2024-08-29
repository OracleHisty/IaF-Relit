package com.github.alexthe666.iceandfire.item;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class DragonArmor extends ArmorItem implements IProtectAgainstDragonItem {

    public DragonArmor(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }
}
