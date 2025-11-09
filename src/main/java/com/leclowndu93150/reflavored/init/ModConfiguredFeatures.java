package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.RedwoodForest;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaPineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD = createKey("redwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_REDWOOD = createKey("mega_redwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREES = createKey("redwood_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BROWN_MUSHROOM = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.parse("minecraft:patch_brown_mushroom"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_RED_MUSHROOM = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.parse("minecraft:patch_red_mushroom"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TAIGA_GRASS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.parse("minecraft:patch_taiga_grass"));
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DOUGLAS_IRIS = createKey("patch_douglas_iris");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TRILLIUM = createKey("patch_trillium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ALPINE_LILY = createKey("patch_alpine_lily");

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(RedwoodForest.MODID, name));
    }

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<PlacedFeature> placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        
        register(context, REDWOOD, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.REDWOOD_LOG.get()),
                new StraightTrunkPlacer(5, 2, 1),
                BlockStateProvider.simple(ModBlocks.REDWOOD_LEAVES.get()),
                new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)),
                new TwoLayersFeatureSize(2, 0, 2)
        ).ignoreVines().build());

        register(context, MEGA_REDWOOD, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.REDWOOD_LOG.get()),
                new GiantTrunkPlacer(13, 2, 14),
                BlockStateProvider.simple(ModBlocks.REDWOOD_LEAVES.get()),
                new MegaPineFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), UniformInt.of(13, 17)),
                new TwoLayersFeatureSize(1, 1, 2)
        ).decorators(List.of(new AlterGroundDecorator(BlockStateProvider.simple(Blocks.PODZOL)))).build());
        
        register(context, REDWOOD_TREES, Feature.RANDOM_SELECTOR, 
                new RandomFeatureConfiguration(
                        List.of(new WeightedPlacedFeature(
                                placedFeatures.getOrThrow(ModPlacedFeatures.MEGA_REDWOOD_CHECKED), 0.33333334f
                        )),
                        placedFeatures.getOrThrow(ModPlacedFeatures.REDWOOD_CHECKED)
                ));

        register(context, PATCH_DOUGLAS_IRIS, Feature.FLOWER,
                new RandomPatchConfiguration(32, 6, 2, placedFeatures.getOrThrow(ModPlacedFeatures.DOUGLAS_IRIS_PLACED)));

        register(context, PATCH_TRILLIUM, Feature.FLOWER,
                new RandomPatchConfiguration(48, 6, 2, placedFeatures.getOrThrow(ModPlacedFeatures.TRILLIUM_PLACED)));

        register(context, PATCH_ALPINE_LILY, Feature.FLOWER,
                new RandomPatchConfiguration(16, 5, 2, placedFeatures.getOrThrow(ModPlacedFeatures.ALPINE_LILY_PLACED)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
