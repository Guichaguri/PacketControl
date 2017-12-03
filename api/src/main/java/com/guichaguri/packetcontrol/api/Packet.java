package com.guichaguri.packetcontrol.api;

/**
 * Represents a packet.
 *
 * @see PacketService#createPacket(PacketType)
 * @see PacketService#createPacket(PacketType, Object)
 * @see PacketService#sendPacket
 * @author Guichaguri
 */
public interface Packet {

    /**
     * Gets the packet type
     *
     * @see PacketTypes
     * @return The type
     */
    PacketType getType();

}
