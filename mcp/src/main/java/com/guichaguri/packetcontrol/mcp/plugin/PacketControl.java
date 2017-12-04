package com.guichaguri.packetcontrol.mcp.plugin;

import com.guichaguri.packetcontrol.api.PacketService;
import com.guichaguri.packetcontrol.api.PacketType;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;

/**
 * @author Guichaguri
 */
@Plugin(
        id = "packetcontrol",
        name = "PacketControl",
        authors = "Guichaguri",
        description = "A protocol library for Sponge"
)
public class PacketControl {

    @Listener
    public void preStart(GamePreInitializationEvent event) {
        Sponge.getRegistry().registerModule(PacketType.class, PacketRegistryModule.getInstance());
        Sponge.getServiceManager().setProvider(this, PacketService.class, new PacketControlService());
    }

}
