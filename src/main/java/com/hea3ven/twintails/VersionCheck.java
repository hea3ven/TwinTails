package com.hea3ven.twintails;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.util.ChatComponentTranslation;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

public class VersionCheck {

    public static class VersionInformer {
        @SubscribeEvent
        public void onPlayerTick(PlayerTickEvent event) {
            if (checkDone) {
                if (latestVersion != null
                        && !latestVersion.equals(TwinTailsMod.config.versionLatest)) {
                    TwinTailsMod.config.setLatestVersion(latestVersion);
                    if (!TwinTailsMod.VERSION.equals(latestVersion)) {
                        event.player.addChatMessage(new ChatComponentTranslation(
                                "twintails.update", latestVersion));
                    }
                }
                FMLCommonHandler.instance().bus().unregister(this);
            }
        }
    }

    private static boolean checkDone = false;
    private static Thread versionCheckThread;
    private static VersionInformer versionInformer;
    private static String latestVersion = null;

    public static void init() {
        if (!TwinTailsMod.config.versionCheck || checkDone)
            return;

        versionCheckThread = new Thread(new Runnable() {

            @Override
            public void run() {
                VersionCheck.getVersion();
            }
        }, "TwinTails version check Thread");

        versionInformer = new VersionInformer();
        FMLCommonHandler.instance().bus().register(versionInformer);

        versionCheckThread.start();
    }

    protected static void getVersion() {
        try {
            TwinTailsMod.logger.info("Looking for the latest version");
            URL url = new URL("https://raw.githubusercontent.com/hea3ven/TwinTails/master/VERSION");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String versionData = reader.readLine();
            if (versionData.indexOf('|') != -1)
                versionData = versionData.substring(0, versionData.indexOf('|'));
            latestVersion = versionData;
            TwinTailsMod.logger.info("Latest version is {}", versionData);
        } catch (Exception e) {
            TwinTailsMod.logger.error("Could not get the latest version info", e);
        }
        checkDone = true;
    }

}
