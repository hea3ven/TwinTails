package com.hea3ven.twintails.client.model;

import java.util.List;

import com.google.common.base.Function;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class ModelTwinTails extends ModelBiped {
	private final String subType;
    private IModel model;
    private TextureAtlasSprite sprite;

    public ModelTwinTails(String subType) {
		this.subType = subType;
		try {
			model = ModelLoaderRegistry.getModel(
					new ResourceLocation("twintails:twintails_" + subType + ".obj"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

	public void loadTexture(TextureMap map) {
		System.out.println("hi");
		sprite = map.registerSprite(new ResourceLocation("twintails:models/twintails_" + subType));
	}

    @Override
    public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float headAngleY, float headAngleX, float p_78088_7_) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);

		GlStateManager.pushMatrix();
		if (entity.isSneaking()) {
			GlStateManager.translate(0.0F, 0.275F, 0.0F);
		}

		GlStateManager.rotate(headAngleY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(headAngleX, 1.0F, 0.0F, 0.0F);

		Tessellator tess = Tessellator.getInstance();
		WorldRenderer wr = tess.getWorldRenderer();

		if (model != null) {
			IFlexibleBakedModel bakedModel = model.bake(model.getDefaultState(), DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL, new Function<ResourceLocation, TextureAtlasSprite>() {
				@Nullable
				@Override
				public TextureAtlasSprite apply(@Nullable ResourceLocation input) {
					return sprite;
				}
			});

			wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

			for (BakedQuad b : bakedModel.getGeneralQuads()) {
				wr.addVertexData(b.getVertexData());
			}

			for (EnumFacing f : EnumFacing.VALUES) {
				List<BakedQuad> bl = bakedModel.getFaceQuads(f);
				if (bl != null) {
					for (BakedQuad b : bl) {
						wr.addVertexData(b.getVertexData());
					}
				}
			}
			tess.draw();
		}

        GlStateManager.popMatrix();
    }
}
