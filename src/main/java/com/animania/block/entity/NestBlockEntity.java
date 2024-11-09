package com.animania.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class NestBlockEntity extends BlockEntity {
    public NestBlockEntity(BlockPos pos, BlockState blockState) {
        super(AnimaniaBlockEntityTypes.NEST_BLOCK_ENTITY.get(), pos, blockState);
    }
}
