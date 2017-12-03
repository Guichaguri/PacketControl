package com.guichaguri.packetcontrol.api.packets.play.entity;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.EntityHolder;
import com.guichaguri.packetcontrol.api.util.Positionable3d;
import com.guichaguri.packetcontrol.api.util.Rotatable;
import java.util.UUID;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;

/**
 * @author Guichaguri
 */
public interface PacketSpawnObject extends Packet, EntityHolder, Positionable3d, Rotatable {

    UUID getUniqueId();

    void setUniqueId(UUID uuid);

    EntityType getEntityType();

    void setEntityType(EntityType type);

    Vector3d getVelocity();

    void setVelocity(Vector3d velocity);

    void setTypeAndData(Entity entity);

    int getTypeId();

    void setTypeId(int type);

    int getData();

    void setData(int data);

}
