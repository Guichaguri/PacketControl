package com.guichaguri.packetcontrol.api.packets.play.entity;

import com.guichaguri.packetcontrol.api.EntityMetadata;
import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.EntityHolder;

/**
 * @author Guichaguri
 */
public interface PacketEntityMetadata extends Packet, EntityHolder {

    EntityMetadata getMetadata();

    void setMetadata(EntityMetadata metadata);

}
