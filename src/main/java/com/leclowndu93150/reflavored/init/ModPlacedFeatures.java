package com.leclowndu93150.reflavored.init;

import com.jcraft.jorbis.Block;
import com.leclowndu93150.reflavored.Redflavored;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> REDWOOD_CHECKED = createKey("redwood_checked");
    public static final ResourceKey<PlacedFeature> MEGA_REDWOOD_CHECKED = createKey("mega_redwood_checked");
    public static final ResourceKey<PlacedFeature> REDWOOD_TREES = createKey("redwood_trees");

    public static final ResourceKey<PlacedFeature> CYPRESSE_CHECKED = createKey("cypresse_checked");
    public static final ResourceKey<PlacedFeature> CYPRESSE_TREES = createKey("cypresse_trees");

    public static final ResourceKey<PlacedFeature> SPARSE_OLD_GROWTH_SPRUCE_TREES = createKey("sparse_old_growth_spruce_trees");

    public static final ResourceKey<PlacedFeature> BROWN_MUSHROOM_REDWOOD = createKey("brown_mushroom_redwood");
    public static final ResourceKey<PlacedFeature> RED_MUSHROOM_REDWOOD = createKey("red_mushroom_redwood");
    public static final ResourceKey<PlacedFeature> PATCH_GRASS_REDWOOD = createKey("patch_grass_redwood");
    
    public static final ResourceKey<PlacedFeature> DOUGLAS_IRIS_PLACED = createKey("douglas_iris_placed");
    public static final ResourceKey<PlacedFeature> TRILLIUM_PLACED = createKey("trillium_placed");
    public static final ResourceKey<PlacedFeature> ALPINE_LILY_PLACED = createKey("alpine_lily_placed");
    public static final ResourceKey<PlacedFeature> PATCH_DOUGLAS_IRIS = createKey("patch_douglas_iris");
    public static final ResourceKey<PlacedFeature> PATCH_TRILLIUM = createKey("patch_trillium");
    public static final ResourceKey<PlacedFeature> PATCH_ALPINE_LILY = createKey("patch_alpine_lily");

    public static final ResourceKey<PlacedFeature> LAVENDER_PATCH = createKey("lavender_patch");

    public static final ResourceKey<PlacedFeature> PATCH_FIELD_FLOWERS = createKey("patch_field_flowers");

    public static final ResourceKey<PlacedFeature> LAVENDER_ROCK = createKey("lavender_rock");
    public static final ResourceKey<PlacedFeature> LAVENDER_ROCKY_PATCH = createKey("lavender_rocky_patch");
    public static final ResourceKey<PlacedFeature> GRANITE_ROCK = createKey("granite_rock");
    public static final ResourceKey<PlacedFeature> GRANITE_ROCKY_PATCH = createKey("granite_rocky_patch");


    public static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Redflavored.MODID, name));
    }

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, REDWOOD_CHECKED, configuredFeatures.getOrThrow(ModConfiguredFeatures.REDWOOD),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.REDWOOD_SAPLING.get()));

        register(context, MEGA_REDWOOD_CHECKED, configuredFeatures.getOrThrow(ModConfiguredFeatures.MEGA_REDWOOD),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.REDWOOD_SAPLING.get()));


        register(context, REDWOOD_TREES, configuredFeatures.getOrThrow(ModConfiguredFeatures.REDWOOD_TREES),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(8), 7)
                        .add(ConstantInt.of(9), 2)
                        .add(ConstantInt.of(10), 1)
                        .build())),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome());

        register(context,
                SPARSE_OLD_GROWTH_SPRUCE_TREES,
                configuredFeatures.getOrThrow(TreeFeatures.SPRUCE),
                CountPlacement.of(UniformInt.of(0, 2)),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_OCEAN_FLOOR,
                BiomeFilter.biome());

        register(context, CYPRESSE_CHECKED, configuredFeatures.getOrThrow(ModConfiguredFeatures.CYPRESSE),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.LAVENDER.get())); //using the lavender for testing only, //TODO replace with an actual sapling

        register(context, CYPRESSE_TREES,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.CYPRESSE),
                CountPlacement.of(UniformInt.of(0, 2)),
                InSquarePlacement.spread(),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());


        register(context, BROWN_MUSHROOM_REDWOOD, configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_BROWN_MUSHROOM),
                RarityFilter.onAverageOnceEvery(4),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());

        register(context, RED_MUSHROOM_REDWOOD, configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_RED_MUSHROOM),
                RarityFilter.onAverageOnceEvery(8),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP,
                BiomeFilter.biome());

        register(context, PATCH_GRASS_REDWOOD, configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_TAIGA_GRASS),
                CountPlacement.of(5),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        register(context, DOUGLAS_IRIS_PLACED, configuredFeatures.getOrThrow(net.minecraft.data.worldgen.features.VegetationFeatures.PATCH_GRASS),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.DOUGLAS_IRIS.get()));

        register(context, TRILLIUM_PLACED, configuredFeatures.getOrThrow(net.minecraft.data.worldgen.features.VegetationFeatures.PATCH_GRASS),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.TRILLIUM.get()));

        register(context, ALPINE_LILY_PLACED, configuredFeatures.getOrThrow(net.minecraft.data.worldgen.features.VegetationFeatures.PATCH_GRASS),
                PlacementUtils.filteredByBlockSurvival(ModBlocks.ALPINE_LILY.get()));

        register(context, PATCH_DOUGLAS_IRIS,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_DOUGLAS_IRIS),
                RarityFilter.onAverageOnceEvery(4),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        register(context, PATCH_TRILLIUM,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_TRILLIUM),
                RarityFilter.onAverageOnceEvery(3),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        register(context, PATCH_ALPINE_LILY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_ALPINE_LILY),
                RarityFilter.onAverageOnceEvery(6),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        register(context, LAVENDER_PATCH,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_LAVENDER),
                CountPlacement.of(UniformInt.of(10, 20)),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        register(context, PATCH_FIELD_FLOWERS,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.PATCH_FIELD_FLOWERS),
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());


        register(context,
                LAVENDER_ROCK,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LAVENDER_ROCK),
                RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        register(context,
                LAVENDER_ROCKY_PATCH,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.LAVENDER_ROCKY_PATCH),
                RarityFilter.onAverageOnceEvery(6),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        register(context,
                GRANITE_ROCK,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GRANITE_ROCK),
                RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());

        register(context,
                GRANITE_ROCKY_PATCH,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GRANITE_ROCKY_PATCH),
                RarityFilter.onAverageOnceEvery(6),
                InSquarePlacement.spread(),
                PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                BiomeFilter.biome());


    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                  Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
                                  Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }
}
