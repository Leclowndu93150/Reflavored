package com.leclowndu93150.reflavored.mixin;

import com.leclowndu93150.reflavored.duck.IGreyFoxAccessor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Fox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Fox.class)
public abstract class FoxTypeMixin implements IGreyFoxAccessor {
    @Unique
    private static final EntityDataAccessor<Boolean> DATA_IS_GREY_FOX = SynchedEntityData.defineId(Fox.class, EntityDataSerializers.BOOLEAN);
    
    @Inject(method = "defineSynchedData", at = @At("RETURN"))
    private void addGreyFoxData(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(DATA_IS_GREY_FOX, false);
    }
    
    @Override
    public boolean reflavored$isGreyFox() {
        return ((Fox)(Object)this).getEntityData().get(DATA_IS_GREY_FOX);
    }
    
    @Override
    public void reflavored$setGreyFox(boolean value) {
        ((Fox)(Object)this).getEntityData().set(DATA_IS_GREY_FOX, value);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("RETURN"))
    private void saveGreyFoxData(CompoundTag tag, CallbackInfo ci) {
        tag.putBoolean("GreyFox", this.reflavored$isGreyFox());
    }

    @Inject(method = "readAdditionalSaveData", at = @At("RETURN"))
    private void loadGreyFoxData(CompoundTag tag, CallbackInfo ci) {
        if (tag.contains("GreyFox")) {
            this.reflavored$setGreyFox(tag.getBoolean("GreyFox"));
        }
    }

    @Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Fox;", at = @At("RETURN"))
    private void inheritGreyFox(ServerLevel level, AgeableMob otherParent, CallbackInfoReturnable<Fox> cir) {
        Fox baby = cir.getReturnValue();
        if (baby != null && this.reflavored$isGreyFox() && otherParent instanceof Fox) {
            ((IGreyFoxAccessor)baby).reflavored$setGreyFox(true);
        }
    }
}
