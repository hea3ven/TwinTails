package com.hea3ven.twintails;

import com.hea3ven.twintails.conf.TwinTailsConfig;
import com.hea3ven.twintails.item.ItemHairBand;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = TwinTailsMod.MODID,
        version = TwinTailsMod.VERSION,
        guiFactory="com.hea3ven.twintails.conf.TwinTailsConfigGuiFactory")
public class TwinTailsMod {

    public static final String MODID = "twintails";
    public static final String VERSION = "1.0.0";

    @Instance(TwinTailsMod.MODID)
    public static TwinTailsMod instance;

    @SidedProxy(clientSide = "com.hea3ven.twintails.client.TwinTailsClientProxy", serverSide = "com.hea3ven.twintails.TwinTailsCommonProxy")
    public static TwinTailsCommonProxy proxy;

    public static TwinTailsConfig config = new TwinTailsConfig();

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
    }
}
