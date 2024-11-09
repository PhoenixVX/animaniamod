package com.animania.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.Block;

public class NestBlock extends Block {
    public static final MapCodec<NestBlock> CODEC = simpleCodec(NestBlock::new);

    public NestBlock(Properties properties) {
        super(properties);
    }
}
