package com.leclowndu93150.reflavored.client.renderer;

import com.leclowndu93150.reflavored.RedwoodForest;
import com.leclowndu93150.reflavored.duck.IGreyFoxAccessor;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.FoxRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Fox;

public class ModFoxRenderer extends FoxRenderer {
    private static final ResourceLocation GREY_FOX_TEXTURE = ResourceLocation.fromNamespaceAndPath(RedwoodForest.MODID, "textures/entity/fox/grey_fox.png");
    private static final ResourceLocation GREY_FOX_SLEEP_TEXTURE = ResourceLocation.fromNamespaceAndPath(RedwoodForest.MODID, "textures/entity/fox/sleeping_grey_fox.png");

    public ModFoxRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(Fox fox) {
        if (((IGreyFoxAccessor)fox).reflavored$isGreyFox()) {
            return fox.isSleeping() ? GREY_FOX_SLEEP_TEXTURE : GREY_FOX_TEXTURE;
        }
        
        return super.getTextureLocation(fox);
    }
}
