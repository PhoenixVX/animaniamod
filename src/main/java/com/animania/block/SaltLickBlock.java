package com.animania.block;

import com.animania.block.entity.SaltLickBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SaltLickBlock extends BaseEntityBlock {
    public static final MapCodec<SaltLickBlock> CODEC = simpleCodec(SaltLickBlock::new);

    private static final VoxelShape SHAPE = Shapes.box(0.1875D, 0, 0.1875D, 0.8125D, 0.25D, 0.8125D);

    public SaltLickBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    // TODO: Decrease salt lick size when used
    @Override
    protected @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected @NotNull VoxelShape getVisualShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return SHAPE;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new SaltLickBlockEntity(pos, state);
    }
}
