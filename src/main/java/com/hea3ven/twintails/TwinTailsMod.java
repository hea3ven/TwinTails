package com.hea3ven.twintails;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.hea3ven.tools.bootstrap.Bootstrap;

@Mod(modid = TwinTailsMod.MODID, version = TwinTailsMod.VERSION, dependencies = TwinTailsMod.DEPENDENCIES,
		guiFactory = "com.hea3ven.twintails.ModGuiFactoryTwinTails",
		updateJSON = "https://raw.githubusercontent.com/hea3ven/TwinTails/master/media/update.json")
public class TwinTailsMod {

	public static final String MODID = "twintails";
	public static final String VERSION = "PROJECTVERSION";
	public static final String DEPENDENCIES = "required-after:Forge@[FORGEVERSION,)";

	static {
		Bootstrap.init();
	}

	static TwinTailsCommonProxy proxy = new TwinTailsCommonProxy();

	@EventHandler
	public void onPreInitEvent(FMLPreInitializationEvent event) {
		proxy.onPreInitEvent(event);
	}

	@EventHandler
	public void onInitEvent(FMLInitializationEvent event) {
		proxy.onInitEvent(event);
	}

	@EventHandler
	public void onPostInitEvent(FMLPostInitializationEvent event) {
		proxy.onPostInitEvent(event);
	}
}
