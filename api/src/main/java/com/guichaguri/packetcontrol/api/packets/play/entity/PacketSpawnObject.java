package com.guichaguri.packetcontrol.api.packets.play.entity;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.EntityHolder;
import com.guichaguri.packetcontrol.api.util.Motionable;
import com.guichaguri.packetcontrol.api.util.Positionable3d;
import com.guichaguri.packetcontrol.api.util.Rotatable;
import java.util.UUID;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;

/**
 * @author Guichaguri
 */
public interface PacketSpawnObject extends Packet, EntityHolder, Positionable3d, Rotatable, Motionable {

    UUID getUniqueId();

    void setUniqueId(UUID uuid);

    EntityType getEntityType();

    void setEntityType(EntityType type);

    void setTypeAndData(Entity entity);

    int getTypeId();

    void setTypeId(int type);

    int getData();

    void setData(int data);

}
