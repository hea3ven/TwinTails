package com.hea3ven.twintails.conf;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.Configuration;

import com.hea3ven.twintails.TwinTailsMod;

import net.minecraftforge.fml.client.config.GuiConfig;

public class TwinTailsConfigGui extends GuiConfig {

    @SuppressWarnings("unchecked")
    public TwinTailsConfigGui(GuiScreen screen) {
        super(screen,
                TwinTailsMod.config.getConfigElements(Configuration.CATEGORY_GENERAL),
                TwinTailsMod.MODID,
                false,
                false,
                GuiConfig.getAbridgedConfigPath(TwinTailsMod.config.getPath()));
    }
}
