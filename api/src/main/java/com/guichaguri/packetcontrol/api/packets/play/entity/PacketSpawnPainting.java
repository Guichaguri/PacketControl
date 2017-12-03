package com.guichaguri.packetcontrol.api.packets.play.entity;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.EntityHolder;
import com.guichaguri.packetcontrol.api.util.Positionable3d;
import java.util.UUID;
import org.spongepowered.api.data.type.Art;
import org.spongepowered.api.util.Direction;

/**
 * @author Guichaguri
 */
public interface PacketSpawnPainting extends Packet, EntityHolder, Positionable3d {

    UUID getUniqueId();

    void setUniqueId(UUID uuid);

    Art getArt();

    void setArt(Art art);

    Direction getDirection();

    void setDirection(Direction direction);

}
