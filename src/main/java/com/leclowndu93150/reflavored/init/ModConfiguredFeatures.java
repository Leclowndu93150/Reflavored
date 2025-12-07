package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.Redflavored;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.BlockCollisions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.GiantTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.neoforged.fml.common.Mod;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD = createKey("redwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESSE = createKey("cypresse");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEGA_REDWOOD = createKey("mega_redwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREES = createKey("redwood_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BROWN_MUSHROOM = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.parse("minecraft:patch_brown_mushroom"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_RED_MUSHROOM = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.parse("minecraft:patch_red_mushroom"));
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TAIGA_GRASS = ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.parse("minecraft:patch_taiga_grass"));

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DOUGLAS_IRIS = createKey("patch_douglas_iris");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TRILLIUM = createKey("patch_trillium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ALPINE_LILY = createKey("patch_alpine_lily");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_LAVENDER = createKey("patch_lavender");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_FIELD_FLOWERS = createKey("patch_field_flowers");

    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVENDER_ROCK = createKey("lavender_rock");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVENDER_ROCKY_PATCH = createKey("lavender_rocky_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_ROCK = createKey("granite_rock");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_ROCKY_PATCH = createKey("granite_rocky_patch");


    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Redflavored.MODID, name));
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

        register(context, CYPRESSE, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.SPRUCE_LOG),
                        new StraightTrunkPlacer(8, 2, 1),
                        BlockStateProvider.simple(Blocks.SPRUCE_LEAVES),
                        new SpruceFoliagePlacer(UniformInt.of(2, 3), UniformInt.of(0, 2), UniformInt.of(1, 2)),
                        new TwoLayersFeatureSize(2, 0, 2)
                )
                        .decorators(List.of(new BeehiveDecorator(0.05F))).ignoreVines().build()
                        );


        register(context, PATCH_DOUGLAS_IRIS, Feature.FLOWER,
                new RandomPatchConfiguration(
                        32,
                        6,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBlocks.DOUGLAS_IRIS.get())
                                )
                        )
                ));

        register(context, PATCH_TRILLIUM, Feature.FLOWER,
                new RandomPatchConfiguration(
                        48,
                        6,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBlocks.TRILLIUM.get())
                                )
                        )
                ));

        register(context, PATCH_ALPINE_LILY, Feature.FLOWER,
                new RandomPatchConfiguration(
                        16,
                        6,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBlocks.ALPINE_LILY.get())
                                )
                        )
                ));

        register(context, PATCH_LAVENDER, Feature.FLOWER,
                new RandomPatchConfiguration(
                        22,
                        6,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBlocks.LAVENDER.get())
                                )
                        )
                ));

        register(context, PATCH_FIELD_FLOWERS, Feature.FLOWER,
                new RandomPatchConfiguration(
                        64,
                        6,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new WeightedStateProvider(
                                                SimpleWeightedRandomList.<net.minecraft.world.level.block.state.BlockState>builder()
                                                        .add(Blocks.DANDELION.defaultBlockState(), 2)
                                                        .add(Blocks.CORNFLOWER.defaultBlockState(), 3)
                                                        .add(Blocks.AZURE_BLUET.defaultBlockState(), 1)
                                                        .build()
                                        )
                                )
                        )
                )
        );


        BlockStateProvider lavenderRocksProvider =
                new WeightedStateProvider(
                        SimpleWeightedRandomList.<BlockState>builder()
                                .add(Blocks.STONE.defaultBlockState(), 3)
                                .add(Blocks.ANDESITE.defaultBlockState(), 2)
                                .add(ModBlocks.MOSSY_ANDESITE.get().defaultBlockState(), 2)
                                .add(ModBlocks.MOSSY_STONE.get().defaultBlockState(), 2)
                                .build()
                );

        RuleBasedBlockStateProvider lavenderRocksRuleProvider =
                new RuleBasedBlockStateProvider(
                        lavenderRocksProvider,
                        java.util.List.of()
                );


        register(context, LAVENDER_ROCK, Feature.BLOCK_PILE,
                new BlockPileConfiguration(
                        lavenderRocksProvider
                )
        );

        register(context, LAVENDER_ROCKY_PATCH, Feature.DISK,
                new DiskConfiguration(
                        lavenderRocksRuleProvider,
                        BlockPredicate.matchesBlocks(
                                java.util.List.of(
                                        Blocks.DIRT,
                                        Blocks.GRASS_BLOCK,
                                        Blocks.PODZOL,
                                        Blocks.COARSE_DIRT,
                                        Blocks.MYCELIUM
                                )
                        ),
                        UniformInt.of(2, 3),
                        2
                )
        );

        register(context, GRANITE_ROCK, Feature.BLOCK_PILE,
                new BlockPileConfiguration(
                        BlockStateProvider.simple(Blocks.GRANITE)
                )
        );

        register(context, GRANITE_ROCKY_PATCH, Feature.DISK,
                new DiskConfiguration(
                        RuleBasedBlockStateProvider.simple(Blocks.GRANITE),
                        BlockPredicate.matchesBlocks(
                                java.util.List.of(
                                        Blocks.DIRT,
                                        Blocks.GRASS_BLOCK,
                                        Blocks.PODZOL,
                                        Blocks.COARSE_DIRT,
                                        Blocks.MYCELIUM
                                )
                        ),
                        UniformInt.of(2, 3),
                        2
                )
        );
    }


    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> context,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
