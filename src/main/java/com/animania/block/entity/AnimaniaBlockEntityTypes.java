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

    public static final Supplier<BlockEntityType<SaltLickBlockEntity>> SALT_LICK_BLOCK_ENTITY = registerBlockEntityType("salt_lick", SaltLickBlockEntity::new, AnimaniaBlocks.SALT_LICK_BLOCK);
    public static final Supplier<BlockEntityType<TroughBlockEntity>> TROUGH_BLOCK_ENTITY = registerBlockEntityType("trough", TroughBlockEntity::new, AnimaniaBlocks.TROUGH_BLOCK);
    public static final Supplier<BlockEntityType<NestBlockEntity>> NEST_BLOCK_ENTITY = registerBlockEntityType("nest", NestBlockEntity::new, AnimaniaBlocks.NEST_BLOCK);

    public static <E extends BlockEntity> Supplier<BlockEntityType<E>> registerBlockEntityType(String name, BlockEntityType.BlockEntitySupplier<E> blockEntitySupplier, Supplier<Block> validBlock) {
        return BLOCK_ENTITY_TYPES.register(name, () -> new BlockEntityType<E>(blockEntitySupplier, validBlock.get()));
    }
}
