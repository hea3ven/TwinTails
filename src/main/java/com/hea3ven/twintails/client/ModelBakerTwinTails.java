package com.hea3ven.twintails.client;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.hea3ven.tools.commonutils.client.ModelBakerBase;
import com.hea3ven.twintails.TwinTailType;
import com.hea3ven.twintails.item.ItemHairBand;

public class ModelBakerTwinTails extends ModelBakerBase {

	private Map<ResourceLocation, TextureAtlasSprite> textures = Maps.newHashMap();

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Pre event) {
		for (TwinTailType type : ItemHairBand.twinTailTypes) {
			ResourceLocation location = new ResourceLocation("twintails:models/twintails_" + type.getName());
			textures.put(location, event.map.registerSprite(location));
		}
	}

	@SubscribeEvent
	public void onModelBake(ModelBakeEvent event) {
		for (final TwinTailType type : ItemHairBand.twinTailTypes) {
			IModel model = getModel(new ResourceLocation("twintails:twintails_" + type.getName() + ".obj"));
			if (model instanceof OBJModel)
				model = ((OBJModel) model).process(ImmutableMap.of("flip-v", "true"));
			IBakedModel bakedModel = bake(model, DefaultVertexFormats.POSITION_TEX_COLOR_NORMAL, textures);
			type.getModel().setModel(bakedModel);
		}
	}
}
