package com.hea3ven.twintails.client;

import com.hea3ven.twintails.TwinTailType;
import com.hea3ven.twintails.TwinTailsCommonProxy;
import com.hea3ven.twintails.TwinTailsMod;
import com.hea3ven.twintails.client.model.ModelTwinTails;

import net.minecraft.client.resources.model.ModelResourceLocation;

import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class TwinTailsClientProxy extends TwinTailsCommonProxy {
	@Override
	public void initModels() {
		OBJLoader.instance.addDomain("twintails");

		for (TwinTailType type : TwinTailsMod.hairBand.getTypes()) {
			type.setModel(new ModelTwinTails(type.getName()));
			ModelLoader.setCustomModelResourceLocation(TwinTailsMod.hairBand, type.getOrdinal(), new ModelResourceLocation("twintails:item/hairband_" + type.getName()));
			ModelLoader.addVariantName(TwinTailsMod.hairBand, "twintails:item/hairband_" + type.getName());
		}
	}

	@SubscribeEvent
	public void onModelBake(ModelBakeEvent event) {
		for (TwinTailType type : TwinTailsMod.hairBand.getTypes()) {
		}
	}

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent.Pre event) {
		for (TwinTailType type : TwinTailsMod.hairBand.getTypes()) {
			type.getModel().loadTexture(event.map);
		}
	}
}
