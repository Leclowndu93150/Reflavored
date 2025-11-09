package com.leclowndu93150.reflavored.datagen;

import com.leclowndu93150.reflavored.init.ModBlocks;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends LootTableProvider {
    public ModLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Set.of(), List.of(
                new SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        ), registries);
    }

    public static class ModBlockLootTables extends BlockLootSubProvider {
        public ModBlockLootTables(HolderLookup.Provider registries) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
        }

        @Override
        protected void generate() {
            dropSelf(ModBlocks.REDWOOD_LOG.get());
            dropSelf(ModBlocks.REDWOOD_WOOD.get());
            dropSelf(ModBlocks.STRIPPED_REDWOOD_LOG.get());
            dropSelf(ModBlocks.STRIPPED_REDWOOD_WOOD.get());
            dropSelf(ModBlocks.REDWOOD_PLANKS.get());
            dropSelf(ModBlocks.REDWOOD_STAIRS.get());
            dropSelf(ModBlocks.REDWOOD_BUTTON.get());
            dropSelf(ModBlocks.REDWOOD_PRESSURE_PLATE.get());
            dropSelf(ModBlocks.REDWOOD_FENCE.get());
            dropSelf(ModBlocks.REDWOOD_FENCE_GATE.get());
            dropSelf(ModBlocks.REDWOOD_TRAPDOOR.get());
            dropSelf(ModBlocks.REDWOOD_SAPLING.get());

            add(ModBlocks.REDWOOD_SLAB.get(), block -> createSlabItemTable(ModBlocks.REDWOOD_SLAB.get()));
            add(ModBlocks.REDWOOD_DOOR.get(), block -> createDoorTable(ModBlocks.REDWOOD_DOOR.get()));
            
            add(ModBlocks.REDWOOD_LEAVES.get(), block -> 
                createLeavesDrops(block, ModBlocks.REDWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

            add(ModBlocks.REDWOOD_SIGN.get(), block -> createSingleItemTable(block));
            add(ModBlocks.REDWOOD_WALL_SIGN.get(), block -> createSingleItemTable(ModBlocks.REDWOOD_SIGN.get()));
            add(ModBlocks.REDWOOD_HANGING_SIGN.get(), block -> createSingleItemTable(block));
            add(ModBlocks.REDWOOD_WALL_HANGING_SIGN.get(), block -> createSingleItemTable(ModBlocks.REDWOOD_HANGING_SIGN.get()));

            dropSelf(ModBlocks.DOUGLAS_IRIS.get());
            dropSelf(ModBlocks.TRILLIUM.get());
            
            add(ModBlocks.ALPINE_LILY.get(), block ->
                    LootTable.lootTable()
                        .withPool(applyExplosionCondition(block,
                            LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .add(LootItem.lootTableItem(block)
                                    .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(block)
                                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                            .hasProperty(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER)))))));
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
        }
    }
}
