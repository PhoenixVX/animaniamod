package com.animania.block;

import com.animania.AnimaniaMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AnimaniaBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AnimaniaMod.MOD_ID);

    // Animania base blocks
    public static final Supplier<Block> MUD_BLOCK = BLOCKS.registerBlock("mud_block", AnimaniaMudBlock::new, BlockBehaviour.Properties.of().strength(1.0F, 1.0F));
    public static final Supplier<Block> STRAW_BLOCK = BLOCKS.registerBlock("straw_block", AnimaniaStrawBlock::new, BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion());
}
