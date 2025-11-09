package com.leclowndu93150.reflavored.init;

import com.leclowndu93150.reflavored.RedwoodForest;
import com.leclowndu93150.reflavored.block.ModCeilingHangingSignBlock;
import com.leclowndu93150.reflavored.block.ModStandingSignBlock;
import com.leclowndu93150.reflavored.block.ModWallHangingSignBlock;
import com.leclowndu93150.reflavored.block.ModWallSignBlock;
import com.leclowndu93150.reflavored.block.AlpineLilyBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RedwoodForest.MODID);

    public static final DeferredBlock<LeavesBlock> REDWOOD_LEAVES = BLOCKS.register("redwood_leaves", 
            () -> new LeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredBlock<RotatedPillarBlock> REDWOOD_LOG = BLOCKS.register("redwood_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    public static final DeferredBlock<RotatedPillarBlock> REDWOOD_WOOD = BLOCKS.register("redwood_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_REDWOOD_LOG = BLOCKS.register("stripped_redwood_log",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    public static final DeferredBlock<RotatedPillarBlock> STRIPPED_REDWOOD_WOOD = BLOCKS.register("stripped_redwood_wood",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    public static final DeferredBlock<Block> REDWOOD_PLANKS = BLOCKS.registerSimpleBlock("redwood_planks",
            BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));

    public static final DeferredBlock<SlabBlock> REDWOOD_SLAB = BLOCKS.register("redwood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));

    public static final DeferredBlock<StairBlock> REDWOOD_STAIRS = BLOCKS.register("redwood_stairs",
            () -> new StairBlock(REDWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_STAIRS)));

    public static final DeferredBlock<DoorBlock> REDWOOD_DOOR = BLOCKS.register("redwood_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR)));

    public static final DeferredBlock<TrapDoorBlock> REDWOOD_TRAPDOOR = BLOCKS.register("redwood_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR)));

    public static final DeferredBlock<FenceBlock> REDWOOD_FENCE = BLOCKS.register("redwood_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));

    public static final DeferredBlock<FenceGateBlock> REDWOOD_FENCE_GATE = BLOCKS.register("redwood_fence_gate",
            () -> new FenceGateBlock(ModWoodTypes.REDWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE)));

    public static final DeferredBlock<PressurePlateBlock> REDWOOD_PRESSURE_PLATE = BLOCKS.register("redwood_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));

    public static final DeferredBlock<ButtonBlock> REDWOOD_BUTTON = BLOCKS.register("redwood_button",
            () -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));

    public static final DeferredBlock<ModStandingSignBlock> REDWOOD_SIGN = BLOCKS.register("redwood_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN), ModWoodTypes.REDWOOD));

    public static final DeferredBlock<ModWallSignBlock> REDWOOD_WALL_SIGN = BLOCKS.register("redwood_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN), ModWoodTypes.REDWOOD));

    public static final DeferredBlock<ModCeilingHangingSignBlock> REDWOOD_HANGING_SIGN = BLOCKS.register("redwood_hanging_sign",
            () -> new ModCeilingHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.REDWOOD));

    public static final DeferredBlock<ModWallHangingSignBlock> REDWOOD_WALL_HANGING_SIGN = BLOCKS.register("redwood_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.REDWOOD));

    public static final DeferredBlock<SaplingBlock> REDWOOD_SAPLING = BLOCKS.register("redwood_sapling",
            () -> new SaplingBlock(ModTreeGrowers.REDWOOD, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<FlowerBlock> DOUGLAS_IRIS = BLOCKS.register("douglas_iris",
            () -> new FlowerBlock(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.ABSORPTION.value()), 6.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));

    public static final DeferredBlock<FlowerBlock> TRILLIUM = BLOCKS.register("trillium",
            () -> new FlowerBlock(BuiltInRegistries.MOB_EFFECT.wrapAsHolder(MobEffects.REGENERATION.value()), 4.0F, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));

    public static final DeferredBlock<AlpineLilyBlock> ALPINE_LILY = BLOCKS.register("alpine_lily",
            () -> new AlpineLilyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SUNFLOWER)));
}
