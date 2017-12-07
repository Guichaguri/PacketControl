package com.guichaguri.packetcontrol.api.packets.login.server;

import com.guichaguri.packetcontrol.api.Packet;
import org.spongepowered.api.text.Text;

/**
 * @author Guichaguri
 */
public interface PacketLoginDisconnect extends Packet {

    Text getReason();

    void setReason(Text text);

}
