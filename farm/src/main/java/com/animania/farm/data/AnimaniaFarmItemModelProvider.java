package com.animania.farm.data;

import com.animania.farm.item.AnimaniaFarmItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.function.BiConsumer;

public class AnimaniaFarmItemModelProvider extends ItemModelGenerators {
    public AnimaniaFarmItemModelProvider(ItemModelOutput itemModelOutput, BiConsumer<ResourceLocation, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }

    @Override
    public void run() {
        for (DeferredHolder<Item, ? extends Item> entry : AnimaniaFarmItems.ITEMS.getEntries()) {
            Item item = entry.get();
            if (item instanceof BlockItem) {
                generateFlatItem(item, ModelTemplates.FLAT_ITEM);
            }
        }
    }
}
