package com.guichaguri.packetcontrol.api.packets.play.client;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.Positionable3i;

/**
 * @author Guichaguri
 */
public interface PacketUpdateSign extends Packet, Positionable3i {

    /**
     * Gets an array with a string for each line
     *
     * @return The array
     */
    String[] getLines();

    /**
     * Sets the lines for the sign.
     *
     * Extra lines will be ignored.
     *
     * @param lines The array of lines
     */
    void setLines(String ... lines);

}
