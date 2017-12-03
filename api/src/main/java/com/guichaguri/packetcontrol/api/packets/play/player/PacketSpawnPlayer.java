package com.guichaguri.packetcontrol.api.packets.play.player;

import com.guichaguri.packetcontrol.api.EntityMetadata;
import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.EntityHolder;
import com.guichaguri.packetcontrol.api.util.Positionable3d;
import com.guichaguri.packetcontrol.api.util.Rotatable;
import java.util.UUID;

/**
 * @author Guichaguri
 */
public interface PacketSpawnPlayer extends Packet, EntityHolder, Positionable3d, Rotatable {

    UUID getUniqueId();

    void setUniqueId(UUID uuid);

    EntityMetadata getMetadata();

    void setMetadata(EntityMetadata metadata);

}
