package com.animania.item;

import com.animania.AnimaniaMod;
import com.animania.block.AnimaniaBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AnimaniaItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AnimaniaMod.MOD_ID);

    // Animania base items
    public static final Supplier<Item> MUD_BLOCK_ITEM = registerBlockItem("mud_block", AnimaniaBlocks.MUD_BLOCK, new Item.Properties());
    public static final Supplier<Item> STRAW_BLOCK_ITEM = registerBlockItem("straw_block", AnimaniaBlocks.STRAW_BLOCK, new Item.Properties());
    public static final Supplier<Item> SALT_LICK_BLOCK_ITEM = registerBlockItem("salt_lick_block", AnimaniaBlocks.SALT_LICK_BLOCK, new Item.Properties());

    private static Supplier<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties properties) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), properties));
    }
}
