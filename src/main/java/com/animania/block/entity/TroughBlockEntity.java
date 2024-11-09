package com.animania.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TroughBlockEntity extends BlockEntity {
    private NonNullList<ItemStack> items = NonNullList.withSize(this.getInventorySize(), ItemStack.EMPTY);

    public TroughBlockEntity(BlockPos pos, BlockState blockState) {
        super(AnimaniaBlockEntityTypes.TROUGH_BLOCK_ENTITY.get(), pos, blockState);
    }

    @Override
    protected void loadAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(this.getInventorySize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, registries);
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag, HolderLookup.@NotNull Provider registries) {
        super.saveAdditional(tag, registries);
        ContainerHelper.saveAllItems(tag, this.items, registries);
    }

    // Utility methods
    public int getInventorySize() {
        return 3;
    }
}
