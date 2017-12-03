package com.guichaguri.packetcontrol.api.packets.play.player;

import com.guichaguri.packetcontrol.api.Packet;

/**
 * @author Guichaguri
 */
public interface PacketPlayerAbilities extends Packet {

    boolean isInvulnerable();

    void setInvulnerable(boolean invulnerable);

    boolean isCreativeMode();

    void setCreativeMode(boolean creativeMode);

    boolean isFlying();

    void setFlying(boolean flying);

    boolean getAllowFlight();

    void setAllowFlight(boolean allowFlight);

    float getFlySpeed();

    void setFlySpeed(float speed);

    float getWalkSpeed();

    void setWalkSpeed(float speed);

}
