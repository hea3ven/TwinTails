package com.hea3ven.twintails.client.model;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
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
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelTwinTails extends ModelBiped {
	private final String subType;
	private IBakedModel bakedModel;
	private TextureAtlasSprite sprite;

	public ModelTwinTails(String subType) {
		this.subType = subType;
	}

	public void loadTexture(TextureMap map) {
		sprite = map.registerSprite(new ResourceLocation("twintails:models/twintails_" + subType));
	}

	public void bakeModel(ModelLoader modelLoader) {
		IModel model = null;
		try {
			model = modelLoader.getModel(new ResourceLocation("twintails:twintails_" + subType + ".obj"));
			model = ((OBJModel) model).process(ImmutableMap.of("flip-v", "true"));
		} catch (IOException e) {
			model = modelLoader.getMissingModel();
		}
		bakedModel = model.bake(model.getDefaultState(), DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL,
				new Function<ResourceLocation, TextureAtlasSprite>() {
					@Nullable
					@Override
					public TextureAtlasSprite apply(@Nullable ResourceLocation input) {
						return sprite;
					}
				});
	}

	@Override
	public void render(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float headAngleY,
			float headAngleX, float p_78088_7_) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);

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
		WorldRenderer wr = tess.getWorldRenderer();

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

		GlStateManager.popMatrix();
	}
}
