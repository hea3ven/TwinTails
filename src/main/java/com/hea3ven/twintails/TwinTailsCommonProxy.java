package com.hea3ven.twintails;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;

import com.hea3ven.tools.commonutils.mod.ProxyModBase;
import com.hea3ven.twintails.client.ModelBakerTwinTails;
import com.hea3ven.twintails.item.ItemHairBand;

public class TwinTailsCommonProxy extends ProxyModBase {

	private static CreativeTabTwinTails creativeTab = new CreativeTabTwinTails();
	private ItemHairBand hairBand;

	public TwinTailsCommonProxy() {
		super(TwinTailsMod.MODID);

		OBJLoader.instance.addDomain("twintails");

		hairBand = new ItemHairBand();
		hairBand.setUnlocalizedName("hairBand").setCreativeTab(creativeTab);

		String[] twinTailTypeVariants = new String[ItemHairBand.twinTailTypes.length];
		int i = 0;
		for (TwinTailType type : ItemHairBand.twinTailTypes)
			twinTailTypeVariants[i++] = "hairband_" + type.getName();

		addItem(hairBand, "hairBand", twinTailTypeVariants);
	}

	@Override
	public void onPreInitEvent() {
		super.onPreInitEvent();

		MinecraftForge.EVENT_BUS.register(new ModelBakerTwinTails());
	}

	@Override
	public void onInitEvent() {
		super.onInitEvent();
		creativeTab.init(hairBand);
	}

	@Override
	public void registerRecipes() {
		hairBand.AddRecipes();
	}
}
