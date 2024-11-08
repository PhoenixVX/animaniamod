package com.animania.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SaltLickBlockEntity extends BlockEntity {
    public SaltLickBlockEntity(BlockPos pos, BlockState blockState) {
        super(AnimaniaBlockEntityTypes.SALT_LICK_BLOCK_ENTITY.get(), pos, blockState);
    }
}
