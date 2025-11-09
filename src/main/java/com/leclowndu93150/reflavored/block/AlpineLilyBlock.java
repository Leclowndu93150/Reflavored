package com.leclowndu93150.reflavored.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class AlpineLilyBlock extends TallFlowerBlock {
    public static final MapCodec<TallFlowerBlock> CODEC = simpleCodec(AlpineLilyBlock::new);

    @Override
    public MapCodec<TallFlowerBlock> codec() {
        return CODEC;
    }

    public AlpineLilyBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }
}
