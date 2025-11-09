package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.init.ModBlocks;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModItems.REDWOOD_PLANKS.get(), 4)
                .requires(ModBlocks.REDWOOD_LOG.get())
                .group("planks")
                .unlockedBy("has_log", has(ModBlocks.REDWOOD_LOG.get()))
                .save(recipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModItems.REDWOOD_PLANKS.get(), 4)
                .requires(ModBlocks.REDWOOD_WOOD.get())
                .group("planks")
                .unlockedBy("has_log", has(ModBlocks.REDWOOD_WOOD.get()))
                .save(recipeOutput, "redwood_planks_from_redwood_wood");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModItems.REDWOOD_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_REDWOOD_LOG.get())
                .group("planks")
                .unlockedBy("has_log", has(ModBlocks.STRIPPED_REDWOOD_LOG.get()))
                .save(recipeOutput, "redwood_planks_from_stripped_redwood_log");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModItems.REDWOOD_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_REDWOOD_WOOD.get())
                .group("planks")
                .unlockedBy("has_log", has(ModBlocks.STRIPPED_REDWOOD_WOOD.get()))
                .save(recipeOutput, "redwood_planks_from_stripped_redwood_wood");

        woodFromLogs(recipeOutput, ModBlocks.REDWOOD_WOOD.get(), ModBlocks.REDWOOD_LOG.get());
        woodFromLogs(recipeOutput, ModBlocks.STRIPPED_REDWOOD_WOOD.get(), ModBlocks.STRIPPED_REDWOOD_LOG.get());

        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.REDWOOD_SLAB.get(), ModBlocks.REDWOOD_PLANKS.get());
        stairBuilder(ModBlocks.REDWOOD_STAIRS.get(), Ingredient.of(ModBlocks.REDWOOD_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.REDWOOD_PLANKS.get()), has(ModBlocks.REDWOOD_PLANKS.get()))
                .save(recipeOutput);

        fence(recipeOutput, ModBlocks.REDWOOD_FENCE.get(), ModBlocks.REDWOOD_PLANKS.get());
        fenceGate(recipeOutput, ModBlocks.REDWOOD_FENCE_GATE.get(), ModBlocks.REDWOOD_PLANKS.get());
        
        door(recipeOutput, ModBlocks.REDWOOD_DOOR.get(), ModBlocks.REDWOOD_PLANKS.get());
        trapdoor(recipeOutput, ModBlocks.REDWOOD_TRAPDOOR.get(), ModBlocks.REDWOOD_PLANKS.get());
        
        pressurePlate(recipeOutput, ModBlocks.REDWOOD_PRESSURE_PLATE.get(), ModBlocks.REDWOOD_PLANKS.get());
        buttonBuilder(ModBlocks.REDWOOD_BUTTON.get(), Ingredient.of(ModBlocks.REDWOOD_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.REDWOOD_PLANKS.get()), has(ModBlocks.REDWOOD_PLANKS.get()))
                .save(recipeOutput);

        signBuilder(ModItems.REDWOOD_SIGN.get(), Ingredient.of(ModBlocks.REDWOOD_PLANKS.get()))
                .unlockedBy(getHasName(ModBlocks.REDWOOD_PLANKS.get()), has(ModBlocks.REDWOOD_PLANKS.get()))
                .save(recipeOutput);

        hangingSign(recipeOutput, ModItems.REDWOOD_HANGING_SIGN.get(), ModBlocks.REDWOOD_PLANKS.get());
        
        woodenBoat(recipeOutput, ModItems.REDWOOD_BOAT.get(), ModBlocks.REDWOOD_PLANKS.get());
        chestBoat(recipeOutput, ModItems.REDWOOD_CHEST_BOAT.get(), ModItems.REDWOOD_BOAT.get());

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PURPLE_DYE)
                .requires(ModBlocks.DOUGLAS_IRIS.get())
                .group("purple_dye")
                .unlockedBy("has_flower", has(ModBlocks.DOUGLAS_IRIS.get()))
                .save(recipeOutput, "purple_dye_from_douglas_iris");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.WHITE_DYE)
                .requires(ModBlocks.TRILLIUM.get())
                .group("white_dye")
                .unlockedBy("has_flower", has(ModBlocks.TRILLIUM.get()))
                .save(recipeOutput, "white_dye_from_trillium");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.ORANGE_DYE)
                .requires(ModBlocks.ALPINE_LILY.get())
                .group("orange_dye")
                .unlockedBy("has_flower", has(ModBlocks.ALPINE_LILY.get()))
                .save(recipeOutput, "orange_dye_from_alpine_lily");
    }

    protected static void fence(RecipeOutput output, ItemLike fence, ItemLike planks) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, fence, 3)
                .define('#', planks)
                .define('S', Items.STICK)
                .pattern("#S#")
                .pattern("#S#")
                .unlockedBy(getHasName(planks), has(planks))
                .save(output);
    }

    protected static void fenceGate(RecipeOutput output, ItemLike fenceGate, ItemLike planks) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, fenceGate)
                .define('#', Items.STICK)
                .define('W', planks)
                .pattern("#W#")
                .pattern("#W#")
                .unlockedBy(getHasName(planks), has(planks))
                .save(output);
    }

    protected static void door(RecipeOutput output, ItemLike door, ItemLike planks) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, door, 3)
                .define('#', planks)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(planks), has(planks))
                .save(output);
    }

    protected static void trapdoor(RecipeOutput output, ItemLike trapdoor, ItemLike planks) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, trapdoor, 2)
                .define('#', planks)
                .pattern("###")
                .pattern("###")
                .unlockedBy(getHasName(planks), has(planks))
                .save(output);
    }
}
