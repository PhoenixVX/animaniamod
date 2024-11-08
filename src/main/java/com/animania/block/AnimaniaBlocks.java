package com.animania.block;

import com.animania.AnimaniaMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class AnimaniaBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AnimaniaMod.MOD_ID);

    // Animania base blocks
    public static final Supplier<Block> MUD_BLOCK = registerBlock("mud_block", AnimaniaMudBlock::new, BlockBehaviour.Properties.of().strength(1.0F, 1.0F).speedFactor(0.6F).sound(SoundType.SLIME_BLOCK).randomTicks());
    public static final Supplier<Block> STRAW_BLOCK = registerBlock("straw_block", AnimaniaStrawBlock::new, BlockBehaviour.Properties.of().sound(SoundType.GRASS).noOcclusion());
    public static final Supplier<Block> SALT_LICK_BLOCK = registerBlock("salt_lick_block", AnimaniaSaltLickBlock::new, BlockBehaviour.Properties.of().strength(1.2F, 1.7F).noOcclusion());

    public static <B extends Block> Supplier<B> registerBlock(String name, Function<BlockBehaviour.Properties, B> block, BlockBehaviour.Properties properties) {
        return BLOCKS.registerBlock(name, block, properties);
    }
}
