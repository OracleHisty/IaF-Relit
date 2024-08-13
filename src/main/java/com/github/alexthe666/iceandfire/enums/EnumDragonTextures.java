package com.github.alexthe666.iceandfire.enums;

import com.github.alexthe666.iceandfire.entity.EntityDragonBase;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

//TODO: Convert into dragon egg based texture.
public class EnumDragonTextures {
    public enum Armor {
        EMPTY(""),
        ARMORBODY1("armor_body_1"),
        ARMORBODY2("armor_body_2"),
        ARMORBODY3("armor_body_3"),
        ARMORBODY4("armor_body_4"),
        ARMORBODY5("armor_body_5"),
        ARMORBODY6("armor_body_6"),
        ARMORBODY7("armor_body_7"),
        ARMORBODY8("armor_body_8"),
        ARMORHEAD1("armor_head_1"),
        ARMORHEAD2("armor_head_2"),
        ARMORHEAD3("armor_head_3"),
        ARMORHEAD4("armor_head_4"),
        ARMORHEAD5("armor_head_5"),
        ARMORHEAD6("armor_head_6"),
        ARMORHEAD7("armor_head_7"),
        ARMORHEAD8("armor_head_8"),
        ARMORNECK1("armor_neck_1"),
        ARMORNECK2("armor_neck_2"),
        ARMORNECK3("armor_neck_3"),
        ARMORNECK4("armor_neck_4"),
        ARMORNECK5("armor_neck_5"),
        ARMORNECK6("armor_neck_6"),
        ARMORNECK7("armor_neck_7"),
        ARMORNECK8("armor_neck_8"),
        ARMORTAIL1("armor_tail_1"),
        ARMORTAIL2("armor_tail_2"),
        ARMORTAIL3("armor_tail_3"),
        ARMORTAIL4("armor_tail_4"),
        ARMORTAIL5("armor_tail_5"),
        ARMORTAIL6("armor_tail_6"),
        ARMORTAIL7("armor_tail_7"),
        ARMORTAIL8("armor_tail_8");

        public final ResourceLocation FIRETEXTURE;
        public final ResourceLocation ICETEXTURE;
        public final ResourceLocation LIGHTNINGTEXTURE;

        Armor(String resource) {
            if (!resource.isEmpty()) {
                FIRETEXTURE = new ResourceLocation("iceandfire:textures/models/firedragon/" + resource + ".png");
                ICETEXTURE = new ResourceLocation("iceandfire:textures/models/icedragon/" + resource + ".png");
                LIGHTNINGTEXTURE = new ResourceLocation("iceandfire:textures/models/lightningdragon/" + resource + ".png");
            } else {
                FIRETEXTURE = new ResourceLocation("iceandfire:textures/models/firedragon/empty.png");
                ICETEXTURE = new ResourceLocation("iceandfire:textures/models/firedragon/empty.png");
                LIGHTNINGTEXTURE = new ResourceLocation("iceandfire:textures/models/firedragon/empty.png");
            }
        }

        public static Armor getArmorForDragon(EntityDragonBase dragon, EquipmentSlot slot) {
            int armor = dragon.getArmorOrdinal(dragon.getItemBySlot(slot));
            switch (slot) {
                case CHEST -> {
                    //neck
                    return switch (armor) {
                        default -> EMPTY;
                        case 1 -> ARMORNECK1;
                        case 2 -> ARMORNECK2;
                        case 3 -> ARMORNECK3;
                        case 4 -> ARMORNECK4;
                        case 5 -> ARMORNECK5;
                        case 6 -> ARMORNECK6;
                        case 7 -> ARMORNECK7;
                        case 8 -> ARMORNECK8;
                    };
                }
                case LEGS -> {
                    //body
                    return switch (armor) {
                        default -> EMPTY;
                        case 1 -> ARMORBODY1;
                        case 2 -> ARMORBODY2;
                        case 3 -> ARMORBODY3;
                        case 4 -> ARMORBODY4;
                        case 5 -> ARMORBODY5;
                        case 6 -> ARMORBODY6;
                        case 7 -> ARMORBODY7;
                        case 8 -> ARMORBODY8;
                    };
                }
                case FEET -> {
                    //tail
                    return switch (armor) {
                        default -> EMPTY;
                        case 1 -> ARMORTAIL1;
                        case 2 -> ARMORTAIL2;
                        case 3 -> ARMORTAIL3;
                        case 4 -> ARMORTAIL4;
                        case 5 -> ARMORTAIL5;
                        case 6 -> ARMORTAIL6;
                        case 7 -> ARMORTAIL7;
                        case 8 -> ARMORTAIL8;
                    };
                }
                default -> {
                    //head
                    return switch (armor) {
                        default -> EMPTY;
                        case 1 -> ARMORHEAD1;
                        case 2 -> ARMORHEAD2;
                        case 3 -> ARMORHEAD3;
                        case 4 -> ARMORHEAD4;
                        case 5 -> ARMORHEAD5;
                        case 6 -> ARMORHEAD6;
                        case 7 -> ARMORHEAD7;
                        case 8 -> ARMORHEAD8;
                    };
                }
            }
        }
    }
}
