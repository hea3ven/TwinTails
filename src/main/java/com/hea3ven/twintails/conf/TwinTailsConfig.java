package com.hea3ven.twintails.conf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import com.hea3ven.twintails.TwinTailsMod;

public class TwinTailsConfig {

    public Boolean twinTailsEffects = true;
    public Boolean versionCheck = true;
    public String versionLatest = null;

    private Configuration configFile;
    private Property twintailsEffectsProp;
    private Property versionCheckProp;
    private Property versionLatestProp;

    public void init(File file) {
        configFile = new Configuration(file);
        twintailsEffectsProp = configFile.get(Configuration.CATEGORY_GENERAL, "TwinTailsEffects",
                false, "Enable to get effects while wearing certain twintails");
        versionCheckProp = configFile.get(Configuration.CATEGORY_GENERAL, "VersionCheck", true,
                "Enable to perform the version check");
        versionLatestProp = configFile.get(Configuration.CATEGORY_GENERAL, "VersionLatest", "",
                "The last version found, do not edit this value");

        syncWithFile();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(TwinTailsMod.MODID))
            syncWithFile();
    }

    private void syncWithFile() {
        twinTailsEffects = twintailsEffectsProp.getBoolean();
        versionCheck = versionCheckProp.getBoolean();
        versionLatest = versionLatestProp.getString();

        if (configFile.hasChanged())
            configFile.save();
    }

    public List<IConfigElement> getConfigElements() {
        List<IConfigElement> elems = new ArrayList<>();
        elems.add(new ConfigElement(twintailsEffectsProp));
        elems.add(new ConfigElement(versionCheckProp));
        return elems;
    }

    public String getPath() {
        return configFile.toString();
    }

    public void setLatestVersion(String version) {
        versionLatestProp.set(version);
        syncWithFile();
    }
}
