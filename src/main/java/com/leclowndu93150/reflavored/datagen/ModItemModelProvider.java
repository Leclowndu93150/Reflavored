package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.RedwoodForest;
import com.leclowndu93150.reflavored.init.ModBlocks;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RedwoodForest.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        blockItem(ModBlocks.REDWOOD_LOG.get());
        blockItem(ModBlocks.REDWOOD_WOOD.get());
        blockItem(ModBlocks.STRIPPED_REDWOOD_LOG.get());
        blockItem(ModBlocks.STRIPPED_REDWOOD_WOOD.get());
        blockItem(ModBlocks.REDWOOD_PLANKS.get());
        blockItem(ModBlocks.REDWOOD_LEAVES.get());
        blockItem(ModBlocks.REDWOOD_STAIRS.get());
        blockItem(ModBlocks.REDWOOD_SLAB.get());
        blockItem(ModBlocks.REDWOOD_FENCE_GATE.get());
        blockItem(ModBlocks.REDWOOD_PRESSURE_PLATE.get());
        blockItemWithSuffix(ModBlocks.REDWOOD_TRAPDOOR.get(), "_bottom");
        
        OurfenceInventory("redwood_fence", modLoc("block/redwood_planks"));
        OurbuttonInventory("redwood_button", modLoc("block/redwood_planks"));
        
        generatedItem(ModItems.REDWOOD_DOOR);
        generatedItem(ModItems.REDWOOD_SAPLING);
        generatedItem(ModItems.REDWOOD_SIGN);
        generatedItem(ModItems.REDWOOD_HANGING_SIGN);
        generatedItem(ModItems.REDWOOD_BOAT);
        generatedItem(ModItems.REDWOOD_CHEST_BOAT);

        generatedItem(ModItems.DOUGLAS_IRIS);
        generatedItem(ModItems.TRILLIUM);
        generatedItem(ModItems.ALPINE_LILY);
    }

    private void blockItem(Block block) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        withExistingParent(name, modLoc("block/" + name));
    }

    private void blockItemWithSuffix(Block block, String suffix) {
        String name = BuiltInRegistries.BLOCK.getKey(block).getPath();
        withExistingParent(name, modLoc("block/" + name + suffix));
    }

    private ItemModelBuilder OurfenceInventory(String name, ResourceLocation texture) {
        return withExistingParent(name, mcLoc("block/fence_inventory"))
                .texture("texture", texture);
    }

    private ItemModelBuilder OurbuttonInventory(String name, ResourceLocation texture) {
        return withExistingParent(name, mcLoc("block/button_inventory"))
                .texture("texture", texture);
    }

    private ItemModelBuilder simpleItem(DeferredItem<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder generatedItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                mcLoc("item/generated")).texture("layer0",
                modLoc("item/" + item.getId().getPath()));
    }
}
