package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.RedwoodForest;
import com.leclowndu93150.reflavored.init.ModBlocks;
import com.leclowndu93150.reflavored.init.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(PackOutput output) {
        super(output, RedwoodForest.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + RedwoodForest.MODID, "Redwood Forest");

        addBlock(ModBlocks.REDWOOD_LOG, "Redwood Log");
        addBlock(ModBlocks.REDWOOD_WOOD, "Redwood Wood");
        addBlock(ModBlocks.STRIPPED_REDWOOD_LOG, "Stripped Redwood Log");
        addBlock(ModBlocks.STRIPPED_REDWOOD_WOOD, "Stripped Redwood Wood");
        addBlock(ModBlocks.REDWOOD_PLANKS, "Redwood Planks");
        addBlock(ModBlocks.REDWOOD_LEAVES, "Redwood Leaves");
        addBlock(ModBlocks.REDWOOD_SAPLING, "Redwood Sapling");
        addBlock(ModBlocks.REDWOOD_STAIRS, "Redwood Stairs");
        addBlock(ModBlocks.REDWOOD_SLAB, "Redwood Slab");
        addBlock(ModBlocks.REDWOOD_FENCE, "Redwood Fence");
        addBlock(ModBlocks.REDWOOD_FENCE_GATE, "Redwood Fence Gate");
        addBlock(ModBlocks.REDWOOD_DOOR, "Redwood Door");
        addBlock(ModBlocks.REDWOOD_TRAPDOOR, "Redwood Trapdoor");
        addBlock(ModBlocks.REDWOOD_PRESSURE_PLATE, "Redwood Pressure Plate");
        addBlock(ModBlocks.REDWOOD_BUTTON, "Redwood Button");
        
        addItem(ModItems.REDWOOD_SIGN, "Redwood Sign");
        addItem(ModItems.REDWOOD_HANGING_SIGN, "Redwood Hanging Sign");
        addItem(ModItems.REDWOOD_BOAT, "Redwood Boat");
        addItem(ModItems.REDWOOD_CHEST_BOAT, "Redwood Boat with Chest");
        
        addBlock(ModBlocks.DOUGLAS_IRIS, "Douglas Iris");
        addBlock(ModBlocks.TRILLIUM, "Trillium");
        addBlock(ModBlocks.ALPINE_LILY, "Alpine Lily");
        
        add("entity.redwood_forest.redwood_boat", "Redwood Boat");
        add("entity.redwood_forest.redwood_chest_boat", "Redwood Boat with Chest");
    }
}
