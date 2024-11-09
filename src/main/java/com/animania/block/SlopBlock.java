package com.animania.block;

import com.animania.fluid.AnimaniaFluids;
import net.minecraft.world.level.block.LiquidBlock;

public class SlopBlock extends LiquidBlock {
    public SlopBlock(Properties properties) {
        super(AnimaniaFluids.SLOP_FLUID.get(), properties);
    }
}
