package com.github.alexthe666.iceandfire.client.render.entity;

import com.github.alexthe666.iceandfire.client.model.ModelDragonEgg;
import com.github.alexthe666.iceandfire.entity.EntityDragonEgg;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;


public class RenderDragonEgg extends LivingEntityRenderer<EntityDragonEgg, ModelDragonEgg<EntityDragonEgg>> {

    public RenderDragonEgg(EntityRendererProvider.Context context) {
        super(context, new ModelDragonEgg(), 0.3F);
    }

    @Override
    protected boolean shouldShowName(EntityDragonEgg entity) {
        return entity.shouldShowName() && entity.hasCustomName();
    }


    @Override
    public @NotNull ResourceLocation getTextureLocation(EntityDragonEgg entity) {
        return entity.getEggType().getTextures().egg();
    }

}
