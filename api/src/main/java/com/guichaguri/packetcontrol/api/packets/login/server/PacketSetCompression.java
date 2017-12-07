package com.guichaguri.packetcontrol.api.packets.login.server;

import com.guichaguri.packetcontrol.api.Packet;

/**
 * @author Guichaguri
 */
public interface PacketSetCompression extends Packet {

    int getThreshold();

    void setThreshold(int threshold);

}
