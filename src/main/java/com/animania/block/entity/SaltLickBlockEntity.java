package com.animania.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class SaltLickBlockEntity extends BlockEntity {
    // TODO: Move value to config
    private int usesLeft = 200;

    public SaltLickBlockEntity(BlockPos pos, BlockState blockState) {
        super(AnimaniaBlockEntityTypes.SALT_LICK_BLOCK_ENTITY.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("usesLeft", this.usesLeft);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        this.usesLeft = tag.getInt("usesLeft");
    }

    public int getUsesLeft() {
        return this.usesLeft;
    }

    public void setUsesLeft(int usesLeft) {
        this.usesLeft = usesLeft;
    }
}
