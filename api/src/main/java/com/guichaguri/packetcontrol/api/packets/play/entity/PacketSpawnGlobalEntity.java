package com.guichaguri.packetcontrol.api.packets.play.entity;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.EntityHolder;
import com.guichaguri.packetcontrol.api.util.Positionable3d;
import org.spongepowered.api.entity.EntityType;

/**
 * @author Guichaguri
 */
public interface PacketSpawnGlobalEntity extends Packet, EntityHolder, Positionable3d {

    EntityType getEntityType();

    void setEntityType(EntityType type);

}
