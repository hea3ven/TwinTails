package com.hea3ven.twintails;

import java.util.function.Consumer;

import net.minecraft.item.ItemStack;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.config.Property.Type;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.hea3ven.tools.commonutils.mod.ProxyModBase;
import com.hea3ven.tools.commonutils.mod.config.FileConfigManagerBuilder;
import com.hea3ven.tools.commonutils.util.SidedCall;
import com.hea3ven.twintails.client.ModelBakerTwinTails;
import com.hea3ven.twintails.item.ItemHairBand;

public class TwinTailsCommonProxy extends ProxyModBase {

	private ItemHairBand hairBand;

	public TwinTailsCommonProxy() {
		super(TwinTailsMod.MODID);

		SidedCall.run(Side.CLIENT, new Runnable() {
			@Override
			public void run() {
				OBJLoader.instance.addDomain("twintails");
			}
		});
	}

	@Override
	public void onPreInitEvent(FMLPreInitializationEvent event) {
		super.onPreInitEvent(event);
	}

	@Override
	public void onInitEvent(FMLInitializationEvent event) {
		super.onInitEvent(event);
	}

	@Override
	protected void registerConfig() {
		addConfigManager(new FileConfigManagerBuilder()
				.setFileName("twintails.cfg")
				.setDesc("TwinTails Configuration")
				.addCategory("general")
				.addValue("TwinTailsEffects", "false", Type.BOOLEAN,
						"Enable to get effects while wearing certain twintails", new Consumer<Property>() {
							@Override
							public void accept(Property property) {
								ItemHairBand.twinTailsEffects = property.getBoolean();
							}
						})
				.endCategory()
				.Update(new Consumer<Configuration>() {
					@Override
					public void accept(Configuration configuration) {
						ConfigCategory gralCat = configuration.getCategory("general");
						if (gralCat.containsKey("VersionCheck"))
							gralCat.remove("VersionCheck");
						if (gralCat.containsKey("VersionLatest"))
							gralCat.remove("VersionLatest");
					}
				}));
	}

	@Override
	protected void registerItems() {
		hairBand = (ItemHairBand) new ItemHairBand().setUnlocalizedName("hairBand");
		String[] twinTailTypeVariants = new String[ItemHairBand.twinTailTypes.length];
		int i = 0;
		for (TwinTailType type : ItemHairBand.twinTailTypes)
			twinTailTypeVariants[i++] = "hairband_" + type.getName();
		addItem(hairBand, "hairBand", twinTailTypeVariants);
	}

	@Override
	protected void registerCreativeTabs() {
		addCreativeTab("twintails", new ItemStack(hairBand, 1, 1));
		hairBand.setCreativeTab(getCreativeTab("twintails"));
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected void registerModelBakers() {
		MinecraftForge.EVENT_BUS.register(new ModelBakerTwinTails());
	}

	@Override
	public void registerRecipes() {
		hairBand.AddRecipes();
	}
}
