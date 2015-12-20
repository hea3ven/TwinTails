package com.hea3ven.twintails.conf;

import net.minecraft.client.gui.GuiScreen;

import net.minecraftforge.fml.client.config.GuiConfig;

import com.hea3ven.twintails.TwinTailsMod;

public class TwinTailsConfigGui extends GuiConfig {

	public TwinTailsConfigGui(GuiScreen screen) {
		super(screen, TwinTailsMod.config.getConfigElements(), TwinTailsMod.MODID, false, false,
				GuiConfig.getAbridgedConfigPath(TwinTailsMod.config.getPath()));
	}
}
