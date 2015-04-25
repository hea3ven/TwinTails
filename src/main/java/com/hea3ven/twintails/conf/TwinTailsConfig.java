package com.hea3ven.twintails.conf;

import java.io.File;
import java.util.List;

import com.hea3ven.twintails.TwinTailsMod;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class TwinTailsConfig {
    
    public Boolean twinTailsEffects = true;

    private Configuration configFile;

    public void init(File file) {
        configFile = new Configuration(file);
        
        syncWithFile();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if(event.modID.equals(TwinTailsMod.MODID))
            syncWithFile();
    }

    private void syncWithFile() {
        twinTailsEffects = configFile.getBoolean(
                "TwinTailsEffects",
                Configuration.CATEGORY_GENERAL,
                false,
                "Whether you get effects while wearing twintails or not");
        
        if(configFile.hasChanged())
            configFile.save();
    }

    @SuppressWarnings("rawtypes")
    public List getConfigElements(String category) {
        return new ConfigElement(configFile.getCategory(category)).getChildElements();
    }

    public String getPath() {
        return configFile.toString();
    }
}
