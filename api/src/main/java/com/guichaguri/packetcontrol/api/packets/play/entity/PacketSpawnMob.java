package com.guichaguri.packetcontrol.api.packets.play.entity;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.EntityMetadata;
import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.EntityHolder;
import com.guichaguri.packetcontrol.api.util.Motionable;
import com.guichaguri.packetcontrol.api.util.Positionable3d;
import com.guichaguri.packetcontrol.api.util.Rotatable;
import java.util.UUID;
import org.spongepowered.api.entity.EntityType;

/**
 * @author Guichaguri
 */
public interface PacketSpawnMob extends Packet, EntityHolder, Positionable3d, Rotatable, Motionable {

    UUID getUniqueId();

    void setUniqueId(UUID uuid);

    EntityType getEntityType();

    void setEntityType(EntityType type);

    Vector3d getHeadRotation();

    void setHeadRotation(Vector3d rotation);

    EntityMetadata getMetadata();

    void setMetadata(EntityMetadata metadata);

}
