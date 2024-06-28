package com.github.alexthe666.iceandfire.block;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.entity.tile.BlockEntityDragonforge;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

import static com.github.alexthe666.iceandfire.entity.tile.IafTileEntityRegistry.DRAGONFORGE_CORE;

public class BlockDragonforgeCore extends BaseEntityBlock implements IDragonProof {
    private final DragonType type;

    public BlockDragonforgeCore(DragonType type, boolean activated) {
        super(
            Properties
                .of()
                .mapColor(MapColor.METAL)
                .dynamicShape()
                .strength(40, 500)
                .sound(SoundType.METAL)
                .lightLevel((state) -> activated ? 15 : 0)
        );

        this.type = type;
    }

    static String name(int dragonType, boolean activated) {
        return "dragonforge_%s_core%s".formatted(DragonType.getNameFromInt(dragonType), activated ? "": "_disabled");
    }

    public static void setState(DragonType dragonType, boolean active, Level worldIn, BlockPos pos) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);

        worldIn.setBlock(pos, DragonForge.getCore(dragonType, active).defaultBlockState(), 3);

        if (tileentity != null) {
            tileentity.clearRemoved();
            worldIn.setBlockEntity(tileentity);
        }
    }

    @Override
    public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState state) {
        return PushReaction.BLOCK;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos, Player player, @NotNull InteractionHand handIn, @NotNull BlockHitResult hit) {
        if (!player.isShiftKeyDown()) {
            if (worldIn.isClientSide) {
                IceAndFire.PROXY.setRefrencedTE(worldIn.getBlockEntity(pos));
            } else {
                MenuProvider inamedcontainerprovider = this.getMenuProvider(state, worldIn, pos);
                if (inamedcontainerprovider != null) {
                    player.openMenu(inamedcontainerprovider);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    public ItemStack getItem(Level worldIn, BlockPos pos, BlockState state) {
       return DragonForge.getCore(type, false).asItem().getDefaultInstance();
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(@NotNull BlockState state, Level worldIn, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        BlockEntity tileentity = worldIn.getBlockEntity(pos);
        if (tileentity instanceof BlockEntityDragonforge) {
            Containers.dropContents(worldIn, pos, (BlockEntityDragonforge) tileentity);
            worldIn.updateNeighbourForOutputSignal(pos, this);
            worldIn.removeBlockEntity(pos);
        }
    }

    @Override
    public int getAnalogOutputSignal(@NotNull BlockState blockState, Level worldIn, @NotNull BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromBlockEntity(worldIn.getBlockEntity(pos));
    }

    @Override
    public boolean hasAnalogOutputSignal(@NotNull BlockState state) {
        return true;
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, @NotNull BlockState state, @NotNull BlockEntityType<T> entityType) {
        return createTickerHelper(entityType, DRAGONFORGE_CORE.get(), BlockEntityDragonforge::tick);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new BlockEntityDragonforge(pos, state, type);
    }
}
