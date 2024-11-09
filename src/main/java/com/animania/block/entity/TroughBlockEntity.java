package com.animania.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TroughBlockEntity extends BlockEntity {
    public TroughBlockEntity(BlockPos pos, BlockState blockState) {
        super(AnimaniaBlockEntityTypes.TROUGH_BLOCK_ENTITY.get(), pos, blockState);
    }
}
