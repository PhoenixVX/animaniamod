package com.animania.farm.block;

import com.animania.AnimaniaMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class AnimaniaFarmBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AnimaniaMod.MOD_ID);

    // Wool blocks
    public static final Supplier<Block> DORSET_WOOL = registerBlock("dorset_wool", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL));
    public static final Supplier<Block> FRIESIAN_BLACK_WOOL = registerBlock("friesian_black_wool", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL));
    public static final Supplier<Block> FRIESIAN_BROWN_WOOL = registerBlock("friesian_brown_wool", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL));
    public static final Supplier<Block> JACOB_WOOL = registerBlock("jacob_wool", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
    public static final Supplier<Block> MERINO_BROWN_WOOL = registerBlock("merino_brown_wool", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL));
    public static final Supplier<Block> MERINO_WHITE_WOOL = registerBlock("merino_white_wool", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_WOOL));
    public static final Supplier<Block> SUFFOLK_WOOL = registerBlock("suffolk_wool", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_WOOL));

    public static <B extends Block> Supplier<B> registerBlock(String name, Function<BlockBehaviour.Properties, B> block, BlockBehaviour.Properties properties) {
        return BLOCKS.registerBlock(name, block, properties);
    }
}
