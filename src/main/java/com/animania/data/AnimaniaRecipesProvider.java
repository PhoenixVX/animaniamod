package com.animania.data;

import com.animania.item.AnimaniaItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class AnimaniaRecipesProvider extends RecipeProvider {
    public AnimaniaRecipesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, AnimaniaItems.MUD_BLOCK_ITEM.get(), 4)
                .requires(Items.WATER_BUCKET)
                .requires(Items.DIRT, 2)
                .unlockedBy(getHasName(Items.DIRT), has(Items.WATER_BUCKET))
                .save(recipeOutput);

        // TODO: unlockedBy does not seem right here
        ShapelessRecipeBuilder.shapeless(RecipeCategory.DECORATIONS, AnimaniaItems.NEST_BLOCK_ITEM.get(), 1)
                .requires(ItemTags.WOOL)
                .requires(Items.STICK)
                .requires(ItemTags.LEAVES)
                .unlockedBy(getHasName(Items.STICK), has(ItemTags.LEAVES))
                .unlockedBy(getHasName(Items.STICK), has(ItemTags.WOOL))
                .save(recipeOutput);

        // TODO: This recipe requires salt which is not in Animania base
        // Move to the addon in which salt originates from when we get there
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AnimaniaItems.SALT_LICK_BLOCK_ITEM.get(), 1)
                .requires(Items.WATER_BUCKET)
                .unlockedBy(getHasName(Items.WATER_BUCKET), has(Items.WATER_BUCKET))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, AnimaniaItems.STRAW_BLOCK_ITEM.get(), 1)
                .requires(Items.WHEAT)
                .unlockedBy(getHasName(Items.WHEAT), has(Items.WHEAT))
                .save(recipeOutput);

        // TODO: unlockedBy may not be correct here
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AnimaniaItems.TROUGH_BLOCK_ITEM.get(), 1)
                .pattern("p p")
                .pattern("pip")
                .pattern("s s")
                .define('p', ItemTags.PLANKS)
                .define('i', Items.IRON_INGOT)
                .define('s', Items.STICK)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .unlockedBy(getHasName(Items.OAK_PLANKS), has(ItemTags.PLANKS))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(recipeOutput);
    }
}
