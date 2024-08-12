package com.github.alexthe666.iceandfire.client.render.tile;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;
import com.github.alexthe666.iceandfire.entity.tile.TileEntityGhostChest;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class IceAndFireTEISR extends BlockEntityWithoutLevelRenderer {

    private final BlockEntityRenderDispatcher blockEntityRenderDispatcher;
    private final TileEntityGhostChest chest = new TileEntityGhostChest(BlockPos.ZERO, IafBlockRegistry.GHOST_CHEST.get().defaultBlockState());

    public IceAndFireTEISR(BlockEntityRenderDispatcher dispatcher, EntityModelSet modelSet) {
        super(dispatcher, modelSet);
        this.blockEntityRenderDispatcher = dispatcher;

    }

    @Override
    public void renderByItem(ItemStack stack, @NotNull ItemDisplayContext type, @NotNull PoseStack stackIn, @NotNull MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        if (stack.getItem() == IafBlockRegistry.GHOST_CHEST.get().asItem()) {
            blockEntityRenderDispatcher.renderItem(chest, stackIn, bufferIn, combinedLightIn, combinedOverlayIn);
        }
    }
}
