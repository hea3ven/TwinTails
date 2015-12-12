package com.hea3ven.twintails;

import com.hea3ven.twintails.conf.TwinTailsConfig;
import com.hea3ven.twintails.item.ItemHairBand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = TwinTailsMod.MODID, version = TwinTailsMod.VERSION, guiFactory = "com.hea3ven.twintails.conf.TwinTailsConfigGuiFactory")
public class TwinTailsMod {

    public static final String MODID = "twintails";
    public static final String VERSION = "1.0.3";

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

		proxy.initModels();
		MinecraftForge.EVENT_BUS.register(proxy);
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
