package com.leclowndu93150.reflavored.mixin;

import com.leclowndu93150.reflavored.init.ModBiomes;
import net.minecraft.core.Holder;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(Rabbit.class)
public class RabbitTypeMixin {
    
    @Inject(method = "finalizeSpawn", at = @At("RETURN"))
    private void setBrownVariantInRedwoodForest(ServerLevelAccessor level, DifficultyInstance difficulty, 
                                                 MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, 
                                                 CallbackInfoReturnable<SpawnGroupData> cir) {
        Holder<Biome> biome = level.getBiome(((Rabbit)(Object)this).blockPosition());
        if (biome.is(ModBiomes.REDWOOD_FOREST)) {
            ((Rabbit)(Object)this).setVariant(Rabbit.Variant.BROWN);
        }
    }
}
