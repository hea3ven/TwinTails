package com.hea3ven.twintails.client.model;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTwinTails extends ModelBiped {
	private IBakedModel bakedModel;

	public ModelTwinTails() {
	}

	public void setModel(IBakedModel bakedModel) {
		this.bakedModel = bakedModel;
	}

	@Override
	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float headAngleY,
			float headAngleX, float p_78088_7_) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

		GlStateManager.pushMatrix();
		if (entity.isSneaking()) {
			GlStateManager.translate(0.0F, 0.275F, 0.0F);
		}

		GlStateManager.rotate(headAngleY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(headAngleX, 1.0F, 0.0F, 0.0F);

		// Minecraft renders the player upside-down
		GlStateManager.rotate(180, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(180, 1.0F, 0.0F, 0.0F);

		Tessellator tess = Tessellator.getInstance();
		VertexBuffer wr = tess.getBuffer();

		wr.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL);

		for (BakedQuad b : bakedModel.getQuads(null, null, 0)) {
			wr.addVertexData(b.getVertexData());
		}

		tess.draw();

		GlStateManager.popMatrix();
	}
}
