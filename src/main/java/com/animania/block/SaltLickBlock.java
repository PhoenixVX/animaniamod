package com.animania.block;

import com.animania.AnimaniaConfig;
import com.animania.block.entity.SaltLickBlockEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SaltLickBlock extends Block implements EntityBlock {
    public static final MapCodec<SaltLickBlock> CODEC = simpleCodec(SaltLickBlock::new);

    private static final VoxelShape SHAPE = Shapes.box(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.25D, 0.8125D);

    public SaltLickBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected @NotNull VoxelShape getCollisionShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return getVoxelShape(level, pos);
    }

    @Override
    protected @NotNull VoxelShape getVisualShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return getVoxelShape(level, pos);
    }

    @Override
    protected @NotNull VoxelShape getShape(@NotNull BlockState state, @NotNull BlockGetter level, @NotNull BlockPos pos, @NotNull CollisionContext context) {
        return getVoxelShape(level, pos);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new SaltLickBlockEntity(pos, state);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof SaltLickBlockEntity saltLickBlockEntity) {
            int usesLeft = saltLickBlockEntity.getUsesLeft();
            if (usesLeft >= 0) {
                saltLickBlockEntity.setUsesLeft(usesLeft - 1);
                player.heal(2.0F);
                return InteractionResult.SUCCESS;
            } else {
                // 2 ensures all clients will receive the block change
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    protected @NotNull ItemInteractionResult useItemOn(@NotNull ItemStack stack, @NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull Player player, @NotNull InteractionHand hand, @NotNull BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof SaltLickBlockEntity saltLickBlockEntity) {
            int usesLeft = saltLickBlockEntity.getUsesLeft();
            if (usesLeft >= 0) {
                saltLickBlockEntity.setUsesLeft(usesLeft - 1);
                player.heal(2.0F);
                return ItemInteractionResult.SUCCESS;
            } else {
                // 2 ensures all clients will receive the block change
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
            }
        }
        return ItemInteractionResult.SKIP_DEFAULT_BLOCK_INTERACTION;
    }

    // Utility methods
    @NotNull
    private VoxelShape getVoxelShape(@NotNull BlockGetter level, @NotNull BlockPos pos) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof SaltLickBlockEntity saltLickBlockEntity) {
            double usesLeft = saltLickBlockEntity.getUsesLeft() / (double) AnimaniaConfig.saltLickMaxUses;
            return Shapes.box(0.1875D, 0.0D, 0.1875D, 0.8125D, 0.625D * usesLeft, 0.8125D);
        }
        return SHAPE;
    }
}
