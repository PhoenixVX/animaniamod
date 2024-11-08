package com.animania.block.entity;

import com.animania.AnimaniaMod;
import com.animania.block.AnimaniaBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AnimaniaBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AnimaniaMod.MOD_ID);

    public static final Supplier<BlockEntityType<AnimaniaSaltLickBlockEntity>> SALT_LICK_BLOCK_ENTITY = registerBlockEntityType("salt_lick", AnimaniaSaltLickBlockEntity::new, AnimaniaBlocks.SALT_LICK_BLOCK.get());

    public static <E extends BlockEntity> Supplier<BlockEntityType<E>> registerBlockEntityType(String name, BlockEntityType.BlockEntitySupplier<E> blockEntitySupplier, Block validBlock) {
        return BLOCK_ENTITY_TYPES.register(name, () -> BlockEntityType.Builder.of(blockEntitySupplier, validBlock).build(null));
    }
}
