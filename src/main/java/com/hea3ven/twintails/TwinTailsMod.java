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

@Mod(modid = TwinTailsMod.MODID, version = TwinTailsMod.VERSION,
		guiFactory = "com.hea3ven.twintails.conf.TwinTailsConfigGuiFactory")
public class TwinTailsMod {

	public static final String MODID = "twintails";
	public static final String VERSION = "1.0.3";

	static {
		Bootstrap.require(MODID, "1.0.x");
		Bootstrap.initLib(MODID, "h3nt-commonutils", "1.0.2", "1.0.x");
	}

	@Instance(TwinTailsMod.MODID)
	public static TwinTailsMod instance;

	private TwinTailsCommonProxy proxy = new TwinTailsCommonProxy();

	public static TwinTailsConfig config = new TwinTailsConfig();

	public static Logger logger = LogManager.getLogger("TwinTails");

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		config.init(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(config);

		proxy.onPreInitEvent();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.onInitEvent();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.onPostInitEvent();
		VersionCheck.init();
	}
}
