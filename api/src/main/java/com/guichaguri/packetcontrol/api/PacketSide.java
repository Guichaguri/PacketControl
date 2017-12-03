package com.guichaguri.packetcontrol.api;

/**
 * @author Guichaguri
 */
public enum PacketSide {

    /**
     * Packets that are sent from the server to the client.
     */
    CLIENTBOUND,

    /**
     * Packets that are sent from the client to the server.
     *
     * These packets cannot be created, they can only be received.
     */
    SERVERBOUND

}
