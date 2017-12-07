package com.guichaguri.packetcontrol.api.packets.login.server;

import com.guichaguri.packetcontrol.api.Packet;
import org.spongepowered.api.profile.GameProfile;

/**
 * @author Guichaguri
 */
public interface PacketLoginSuccess extends Packet {

    GameProfile getProfile();

    void setProfile(GameProfile profile);

}
