package com.animania.data;

import com.animania.AnimaniaMod;
import com.animania.item.AnimaniaItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;

public class AnimaniaItemModelProvider extends ItemModelProvider {
    public AnimaniaItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, AnimaniaMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        for (DeferredHolder<Item, ? extends Item> holder : AnimaniaItems.ITEMS.getEntries()) {
            Item item = holder.get();
            if (item instanceof BlockItem blockItem) {
                simpleBlockItem(blockItem.getBlock());
            }
        }
    }
}
