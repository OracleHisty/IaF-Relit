package com.github.alexthe666.iceandfire.block;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.DragonType;
import com.github.alexthe666.iceandfire.entity.tile.BlockEntityDragonforge;
import com.github.alexthe666.iceandfire.entity.tile.TileEntityDragonforgeBrick;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BlockDragonforgeBricks extends BaseEntityBlock implements DragonForgeComponent, IDragonProof {

    public static final BooleanProperty GRILL = BooleanProperty.create("grill");
    private final DragonType dragonType;

    public BlockDragonforgeBricks(DragonType dragonType) {
        super(
            Properties
                .of()
                .mapColor(MapColor.STONE)
                .instrument(NoteBlockInstrument.BASEDRUM)
                .dynamicShape()
                .strength(40, 500)
    			.sound(SoundType.METAL)
		);

        this.dragonType = dragonType;
        this.registerDefaultState(this.getStateDefinition().any().setValue(GRILL, Boolean.FALSE));
    }

    @Override
    public @NotNull PushReaction getPistonPushReaction(@NotNull BlockState state) {
        return PushReaction.BLOCK;
    }

    @Override
    public @NotNull InteractionResult use(@NotNull BlockState state, @NotNull Level worldIn, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand handIn, BlockHitResult resultIn) {
        if (this.getConnectedTileEntity(worldIn, resultIn.getBlockPos()) != null) {
            BlockEntityDragonforge forge = this.getConnectedTileEntity(worldIn, resultIn.getBlockPos());
            if (forge != null && forge.dragonType == dragonType) {
                if (worldIn.isClientSide) {
                    IceAndFire.PROXY.setRefrencedTE(worldIn.getBlockEntity(forge.getBlockPos()));
                } else {
                    MenuProvider inamedcontainerprovider = this.getMenuProvider(forge.getBlockState(), worldIn, forge.getBlockPos());
                    if (inamedcontainerprovider != null) {
                        player.openMenu(inamedcontainerprovider);
                    }
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.FAIL;
    }

    private BlockEntityDragonforge getConnectedTileEntity(Level worldIn, BlockPos pos) {
        for (Direction facing : Direction.values()) {
            if (worldIn.getBlockEntity(pos.relative(facing)) != null && worldIn.getBlockEntity(pos.relative(facing)) instanceof BlockEntityDragonforge) {
                BlockEntityDragonforge forge = (BlockEntityDragonforge) worldIn.getBlockEntity(pos.relative(facing));
                if (forge != null && forge.assembled()) {
                    return forge;
                }
            }
        }
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(GRILL);
    }

    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new TileEntityDragonforgeBrick(pos, state);
    }

    @Override
    public DragonType type() {
        return dragonType;
    }
}
