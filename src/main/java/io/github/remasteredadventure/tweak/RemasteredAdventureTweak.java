package io.github.remasteredadventure.tweak;

import io.github.remasteredadventure.tweak.config.ModConfigHandler;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.network.NetworkConstants;

/**
 * Main entrypoint for Remastered Adventure Tweak.
 */
@Mod(RemasteredAdventureTweak.MOD_ID)
public class RemasteredAdventureTweak {

    public static final String MOD_ID = "remastered_adventure_tweak";

    public RemasteredAdventureTweak() {
        ModLoadingContext.get().registerExtensionPoint(
                IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (remote, isServer) -> true)
        );
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ModConfigHandler.SPEC);
    }
}
