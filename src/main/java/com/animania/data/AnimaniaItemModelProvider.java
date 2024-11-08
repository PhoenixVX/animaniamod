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
                if (holder.equals(AnimaniaItems.SALT_LICK_BLOCK_ITEM)) {
                    withExistingParent("salt_lick_block", "item/generated").texture("layer0", AnimaniaMod.getId("item/salt_lick"));
                } else {
                    simpleBlockItem(blockItem.getBlock());
                }
            } else if (item.equals(AnimaniaItems.SLOP_BUCKET.get())) {
                withExistingParent("slop_bucket", "item/generated").texture("layer0", mcLoc("item/bucket"));
            }
        }
    }
}
