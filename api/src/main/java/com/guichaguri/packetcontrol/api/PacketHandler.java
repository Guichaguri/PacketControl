package com.guichaguri.packetcontrol.api;

import org.spongepowered.api.network.PlayerConnection;

/**
 * A packet handler can intercept incoming and outgoing packets from any connection.
 *
 * @see PacketService#registerHandler(PacketType, PacketHandler)
 * @author Guichaguri
 */
@FunctionalInterface
public interface PacketHandler<P extends Packet> {

    /**
     * Handles an incoming/outgoing packet
     *
     * @param con The connection sending/receiving the packet
     * @param packet The packet
     * @return Whether the packet should not be sent
     */
    boolean handle(PlayerConnection con, P packet);

}
