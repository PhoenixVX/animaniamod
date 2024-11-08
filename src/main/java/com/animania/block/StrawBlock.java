package com.animania.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class StrawBlock extends CarpetBlock {
    private static final VoxelShape VISUAL_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, 0.002D, 1.0D);

    public StrawBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isFlammable(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull Direction direction) {
        return true;
    }

    @Override
    protected @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected @NotNull VoxelShape getVisualShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return VISUAL_SHAPE;
    }

    // TODO: Straw block should not be placeable on glass, canSurvive is probably bugged
    @Override
    protected boolean canSurvive(@NotNull BlockState state, @NotNull LevelReader level, @NotNull BlockPos pos) {
        BlockPos belowPos = pos.below();
        BlockState belowState = level.getBlockState(belowPos);

        boolean isFullBlockBelow = belowState.isCollisionShapeFullBlock(level, belowPos);
        boolean isOpaqueBlockBelow = !belowState.isSolidRender(level, belowPos);
        // TODO: canStay probably shouldn't be checked here.
        boolean canStay = !level.getBlockState(belowPos).isAir();

        return belowState.is(AnimaniaBlocks.STRAW_BLOCK.get()) || !isFullBlockBelow || !isOpaqueBlockBelow || canStay;
    }
}
