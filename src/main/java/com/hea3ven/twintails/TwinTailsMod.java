package com.hea3ven.twintails;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

import com.hea3ven.twintails.conf.TwinTailsConfig;
import com.hea3ven.twintails.item.ItemHairBand;

@Mod(modid = TwinTailsMod.MODID, guiFactory = "com.hea3ven.twintails.conf.TwinTailsConfigGuiFactory")
public class TwinTailsMod {

    public static final String MODID = "twintails";

    @Instance(TwinTailsMod.MODID)
    public static TwinTailsMod instance;

    @SidedProxy(clientSide = "com.hea3ven.twintails.client.TwinTailsClientProxy", serverSide = "com.hea3ven.twintails.TwinTailsCommonProxy")
    public static TwinTailsCommonProxy proxy;

    public static TwinTailsConfig config = new TwinTailsConfig();

    public static Logger logger = LogManager.getLogger("TwinTails");

    public static CreativeTabTwinTails creativeTab = new CreativeTabTwinTails();

    public static ItemHairBand hairBand;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        config.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(config);

        hairBand = new ItemHairBand();
        GameRegistry.registerItem(hairBand, "hairBand");
        hairBand.AddRecipes();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        creativeTab.init();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        VersionCheck.init();
    }
}
