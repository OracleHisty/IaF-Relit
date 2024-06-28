package com.github.alexthe666.iceandfire.compat.jei.firedragonforge;

import com.github.alexthe666.iceandfire.compat.jei.IceAndFireJEIPlugin;
import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.recipe.DragonForgeRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record DragonForgeCategory(DragonType type, DragonForgeDrawable drawable) implements IRecipeCategory<DragonForgeRecipe> {

    public static DragonForgeCategory of(DragonType type) {
        return new DragonForgeCategory(type, DragonForgeDrawable.of(type));
    }

    @Override
    public @NotNull RecipeType<DragonForgeRecipe> getRecipeType() {
        return IceAndFireJEIPlugin.DRAGON_FORGE_RECIPE_TYPES.get(type());
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("iceandfire.fire_dragon_forge");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return drawable();
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return null;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder recipeLayoutBuilder, @NotNull DragonForgeRecipe dragonForgeRecipe, @NotNull IFocusGroup  focuses) {
        Level level = Minecraft.getInstance().level;
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 64, 29)
                .addIngredients(dragonForgeRecipe.getInput());
        recipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 82, 29)
                .addIngredients(dragonForgeRecipe.getBlood());
        if (level != null) {
            recipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 144, 30)
                    .addItemStack(dragonForgeRecipe.getResultItem(level.registryAccess()));
        }
    }

}
