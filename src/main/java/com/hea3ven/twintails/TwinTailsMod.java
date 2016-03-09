package com.hea3ven.twintails;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.hea3ven.tools.bootstrap.Bootstrap;
import com.hea3ven.twintails.conf.TwinTailsConfig;

@Mod(modid = TwinTailsMod.MODID, version = TwinTailsMod.VERSION, dependencies = TwinTailsMod.DEPENDENCIES,
		guiFactory = "com.hea3ven.twintails.conf.TwinTailsConfigGuiFactory")
public class TwinTailsMod {

	public static final String MODID = "twintails";
	public static final String VERSION = "PROJECTVERSION";
	public static final String DEPENDENCIES = "required-after:Forge@[FORGEVERSION,)";

	static {
		Bootstrap.init();
	}

	@Instance(TwinTailsMod.MODID)
	public static TwinTailsMod instance;

	private TwinTailsCommonProxy proxy = new TwinTailsCommonProxy();

	public static TwinTailsConfig config = new TwinTailsConfig();

	public static Logger logger = LogManager.getLogger("TwinTails");

	@EventHandler
	public void onPreInitEvent(FMLPreInitializationEvent event) {
		config.init(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(config);

		proxy.onPreInitEvent(event);
	}

	@EventHandler
	public void onInitEvent(FMLInitializationEvent event) {
		proxy.onInitEvent(event);
	}

	@EventHandler
	public void onPostInitEvent(FMLPostInitializationEvent event) {
		proxy.onPostInitEvent(event);
		VersionCheck.init();
	}
}
