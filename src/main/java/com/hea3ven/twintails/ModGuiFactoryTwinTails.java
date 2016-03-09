package com.hea3ven.twintails;

import net.minecraft.client.gui.GuiScreen;

import com.hea3ven.tools.commonutils.config.ModGuiFactoryAbstract;
import com.hea3ven.tools.commonutils.mod.config.GuiConfigAutomatic;

public class ModGuiFactoryTwinTails extends ModGuiFactoryAbstract {
	@Override
	public Class<? extends GuiScreen> mainConfigGuiClass() {
		return TwinTailsConfigGui.class;
	}

	public static class TwinTailsConfigGui extends GuiConfigAutomatic {
		public TwinTailsConfigGui(GuiScreen parentScreen) {
			super(parentScreen, TwinTailsMod.proxy);
		}
	}
}

