package com.guichaguri.packetcontrol.api.packets.login.client;

import com.guichaguri.packetcontrol.api.Packet;

/**
 * @author Guichaguri
 */
public interface PacketLoginStart extends Packet {

    /**
     * Gets the player username
     *
     * @return The username
     */
    String getName();

    /**
     * Sets the player username
     *
     * @param username The username
     */
    void setName(String username);

}
