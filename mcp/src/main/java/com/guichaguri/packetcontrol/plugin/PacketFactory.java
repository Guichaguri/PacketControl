package com.guichaguri.packetcontrol.plugin;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.PacketType;

/**
 * Allows packets to create custom constructors when needed
 *
 * @see com.guichaguri.packetcontrol.api.PacketService#createPacket(PacketType, Object)
 * @author Guichaguri
 */
@FunctionalInterface
public interface PacketFactory<P extends Packet, A> {

    /**
     * Initializes a new packet using the argument
     * @param argument The argument
     * @return The packet
     */
    P create(A argument);

}
