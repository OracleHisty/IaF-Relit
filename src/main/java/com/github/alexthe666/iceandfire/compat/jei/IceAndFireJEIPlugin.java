package com.github.alexthe666.iceandfire.compat.jei;

import com.github.alexthe666.iceandfire.block.DragonForge;
import com.github.alexthe666.iceandfire.compat.jei.firedragonforge.DragonForgeCategory;
import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.enums.EnumSkullType;
import com.github.alexthe666.iceandfire.item.DragonItems;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.alexthe666.iceandfire.recipe.DragonForgeRecipe;
import com.github.alexthe666.iceandfire.recipe.IafRecipeRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.ModIds;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JeiPlugin
public class IceAndFireJEIPlugin implements IModPlugin {

    public static final ResourceLocation MOD = new ResourceLocation("iceandfire:iceandfire");
    public static final Map<DragonType, RecipeType<DragonForgeRecipe>> DRAGON_FORGE_RECIPE_TYPES = Util.make(new HashMap<>(), map -> {
        for(var type : DragonType.values()) {
            map.put(type, RecipeType.create(ModIds.MINECRAFT_ID, type.getSerializedName() + "_dragon_forge", DragonForgeRecipe.class));
        }
    });

    public IceAndFireJEIPlugin() {

    }


    private void addDescription(IRecipeRegistration registry, ItemStack itemStack) {
        registry.addIngredientInfo(itemStack, VanillaTypes.ITEM_STACK, Component.translatable(itemStack.getDescriptionId() + ".jei_desc"));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        List<DragonForgeRecipe> forgeRecipeList = Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(IafRecipeRegistry.DRAGON_FORGE_TYPE.get());

        for(var type : DragonType.values()) {
            List<DragonForgeRecipe> list = forgeRecipeList.stream().filter(item -> item.getDragonType().equals(type)).toList();
            registry.addRecipes(DRAGON_FORGE_RECIPE_TYPES.get(type), list);

            var dragonItems = DragonItems.getDragonItems(type);
            addDescription(registry, new ItemStack(dragonItems.blood().get()));
            addDescription(registry, new ItemStack(dragonItems.skull().get()));

        }

        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_RED.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_BRONZE.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_GRAY.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_GREEN.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_BLUE.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_WHITE.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_SAPPHIRE.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_SILVER.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_ELECTRIC.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_AMYTHEST.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_COPPER.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.DRAGONEGG_BLACK.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.FIRE_STEW.get()));
        addDescription(registry, new ItemStack(IafItemRegistry.FROST_STEW.get()));

        for (EnumSkullType skull : EnumSkullType.values()) {
            addDescription(registry, new ItemStack(skull.skull_item.get()));
        }

        registry.addIngredientInfo(IafItemRegistry.PATTERN_FIRE.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_ICE.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_LIGHTNING.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_FIRE_HEAD.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_ICE_HEAD.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_LIGHTNING_HEAD.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_AMPHITHERE.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_BIRD.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_EYE.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_FAE.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_FEATHER.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_GORGON.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_HIPPOCAMPUS.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_HIPPOGRYPH_HEAD.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_MERMAID.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_SEA_SERPENT.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_TROLL.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_WEEZER.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
        registry.addIngredientInfo(IafItemRegistry.PATTERN_DREAD.get().getDefaultInstance(), VanillaTypes.ITEM_STACK, Component.translatable("item.iceandfire.custom_banner.jei_desc"));
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        for(var type : DragonType.values()) registry.addRecipeCategories(DragonForgeCategory.of(type));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        for(var type : DragonType.values()) registry.addRecipeCatalyst(new ItemStack(DragonForge.getCore(type, true)), DRAGON_FORGE_RECIPE_TYPES.get(type));
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return MOD;
    }

}
