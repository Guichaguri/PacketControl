package com.guichaguri.packetcontrol.api.packets.login.server;

import com.guichaguri.packetcontrol.api.Packet;

/**
 * @author Guichaguri
 */
public interface PacketEncryptionRequest extends Packet {

    /**
     * Gets the hashed server ID limited to 20 characters
     *
     * This is not currently being used in vanilla, it will always be empty.
     *
     * @return The server id
     */
    String getServerId();

    /**
     * Sets the hashed server ID limited to 20 characters
     *
     * This is not currently being used in vanilla, it will always be empty.
     *
     * @param id The server id
     */
    void setServerId(String id);

    byte[] getPublicKey();

    void setPublicKey(byte[] key);

    byte[] getVerifyToken();

    void setVerifyToken(byte[] token);

}
