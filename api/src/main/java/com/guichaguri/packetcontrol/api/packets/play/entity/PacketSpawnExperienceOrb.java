package com.guichaguri.packetcontrol.api.packets.play.entity;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.EntityHolder;
import com.guichaguri.packetcontrol.api.util.Positionable3d;

/**
 * @author Guichaguri
 */
public interface PacketSpawnExperienceOrb extends Packet, EntityHolder, Positionable3d {

    int getExperience();

    void setExperience(int value);

}
