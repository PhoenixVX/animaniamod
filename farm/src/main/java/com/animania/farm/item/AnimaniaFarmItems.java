package com.animania.farm.item;

import com.animania.AnimaniaMod;
import com.animania.farm.block.AnimaniaFarmBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class AnimaniaFarmItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AnimaniaMod.MOD_ID);

    // Wool block items
    public static final Supplier<Item> DORSET_WOOL_BLOCK_ITEM = registerBlockItem("dorset_wool", AnimaniaFarmBlocks.DORSET_WOOL, new Item.Properties());
    public static final Supplier<Item> FRIESIAN_BLACK_WOOL_BLOCK_ITEM = registerBlockItem("friesian_black_wool", AnimaniaFarmBlocks.FRIESIAN_BLACK_WOOL, new Item.Properties());
    public static final Supplier<Item> FRIESIAN_BROWN_WOOL_BLOCK_ITEM = registerBlockItem("friesian_brown_wool", AnimaniaFarmBlocks.FRIESIAN_BROWN_WOOL, new Item.Properties());
    public static final Supplier<Item> JACOB_WOOL_BLOCK_ITEM = registerBlockItem("jacob_wool", AnimaniaFarmBlocks.JACOB_WOOL, new Item.Properties());
    public static final Supplier<Item> MERINO_BROWN_WOOL_BLOCK_ITEM = registerBlockItem("merino_brown_wool", AnimaniaFarmBlocks.MERINO_BROWN_WOOL, new Item.Properties());
    public static final Supplier<Item> MERINO_WHITE_WOOL_BLOCK_ITEM = registerBlockItem("merino_white_wool", AnimaniaFarmBlocks.MERINO_WHITE_WOOL, new Item.Properties());
    public static final Supplier<Item> SUFFOLK_WOOL_BLOCK_ITEM = registerBlockItem("suffolk_wool", AnimaniaFarmBlocks.SUFFOLK_WOOL, new Item.Properties());

    public static Supplier<Item> registerBlockItem(String name, Supplier<Block> block, Item.Properties itemProperties) {
        return registerItem(name, (properties) -> new BlockItem(block.get(), properties), itemProperties);
    }

    private static Supplier<Item> registerItem(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
        return ITEMS.register(name, () -> factory.apply(properties.setId(ResourceKey.create(Registries.ITEM, AnimaniaMod.getId(name)))));
    }
}
