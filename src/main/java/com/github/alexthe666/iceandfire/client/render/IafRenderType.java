package com.github.alexthe666.iceandfire.client.render;

import com.github.alexthe666.iceandfire.client.IafClientSetup;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class IafRenderType extends RenderType {

    private static final ResourceLocation STONE_TEXTURE = new ResourceLocation("textures/block/stone.png");

    public IafRenderType(String nameIn, VertexFormat formatIn, VertexFormat.Mode drawModeIn, int bufferSizeIn, boolean useDelegateIn, boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
        super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
    }

    public static RenderType getStoneMobRenderType(float x, float y) {
         RenderStateShard.TextureStateShard textureState = new TextureStateShard(STONE_TEXTURE, false, false);
        RenderType.CompositeState rendertype = RenderType.CompositeState.builder().setShaderState(RenderType.RENDERTYPE_ENTITY_CUTOUT_SHADER).setTextureState(textureState).setTransparencyState(NO_TRANSPARENCY).setLightmapState(LIGHTMAP).setOverlayState(OVERLAY).createCompositeState(true);
        return create("stone_entity_type", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, rendertype);
    }

    public static RenderType getIce(ResourceLocation locationIn) {
        TextureStateShard lvt_1_1_ = new TextureStateShard(locationIn, false, false);
        return create("ice_texture", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, RenderType.CompositeState.builder().setShaderState(RenderType.RENDERTYPE_BEACON_BEAM_SHADER).setTextureState(lvt_1_1_).setTransparencyState(TRANSLUCENT_TRANSPARENCY).setCullState(CULL).setLightmapState(LIGHTMAP).setOverlayState(OVERLAY).createCompositeState(true));
    }

    public static RenderType getStoneCrackRenderType(ResourceLocation crackTex) {
        RenderStateShard.TextureStateShard textureState = new TextureStateShard(crackTex, false, false);
        RenderType.CompositeState rendertype$state = RenderType.CompositeState.builder().setTextureState(textureState).setShaderState(RenderType.RENDERTYPE_ENTITY_CUTOUT_SHADER).setTransparencyState(TRANSLUCENT_TRANSPARENCY).setDepthTestState(EQUAL_DEPTH_TEST).setCullState(NO_CULL).setLightmapState(LIGHTMAP).setOverlayState(OVERLAY).createCompositeState(false);
        return create("stone_entity_type_crack", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, rendertype$state);
    }


}
