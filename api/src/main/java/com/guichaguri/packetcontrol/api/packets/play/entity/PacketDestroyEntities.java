package com.guichaguri.packetcontrol.api.packets.play.entity;

import com.guichaguri.packetcontrol.api.Packet;

/**
 * @author Guichaguri
 */
public interface PacketDestroyEntities extends Packet {

    void setEntityIds(int[] entityIds);

    int[] getEntityIds();

}
