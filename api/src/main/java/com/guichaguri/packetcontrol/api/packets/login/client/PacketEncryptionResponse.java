package com.guichaguri.packetcontrol.api.packets.login.client;

import com.guichaguri.packetcontrol.api.Packet;

/**
 * @author Guichaguri
 */
public interface PacketEncryptionResponse extends Packet {

    byte[] getEncryptedSecretKey();

    void setEncryptedSecretKey(byte[] secretKey);

    byte[] getEncryptedVerifyToken();

    void setEncryptedVerifyToken(byte[] token);

}
