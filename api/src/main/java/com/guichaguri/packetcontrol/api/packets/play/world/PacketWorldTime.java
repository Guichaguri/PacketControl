package com.guichaguri.packetcontrol.api.packets.play.world;

import com.guichaguri.packetcontrol.api.Packet;

/**
 * @author Guichaguri
 */
public interface PacketWorldTime extends Packet {

    long getTotalTime();

    void setTotalTime(long time);

    long getWorldTime();

    void setWorldTime(long time);

}
