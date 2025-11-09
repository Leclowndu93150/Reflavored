package com.leclowndu93150.reflavored.client;

import com.leclowndu93150.reflavored.RedwoodForest;
import com.leclowndu93150.reflavored.client.renderer.ModBoatRenderer;
import com.leclowndu93150.reflavored.client.renderer.ModChestBoatRenderer;
import com.leclowndu93150.reflavored.client.renderer.ModFoxRenderer;
import com.leclowndu93150.reflavored.init.ModBlockEntities;
import com.leclowndu93150.reflavored.init.ModBlocks;
import com.leclowndu93150.reflavored.init.ModBoats;
import com.leclowndu93150.reflavored.init.ModWoodTypes;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.FoliageColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = RedwoodForest.MODID, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            Sheets.addWoodType(ModWoodTypes.REDWOOD);
        });
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModBoats.REDWOOD_BOAT.get(),
                context -> new ModBoatRenderer(context, false));
        event.registerEntityRenderer(ModBoats.REDWOOD_CHEST_BOAT.get(),
                ModChestBoatRenderer::new);
        
        event.registerEntityRenderer(EntityType.FOX, ModFoxRenderer::new);
        
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tint) -> 
            level != null && pos != null ? BiomeColors.getAverageFoliageColor(level, pos) : FoliageColor.getDefaultColor(),
            ModBlocks.REDWOOD_LEAVES.get()
        );
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tint) -> FoliageColor.getDefaultColor(),
            ModBlocks.REDWOOD_LEAVES.get()
        );
    }
}
