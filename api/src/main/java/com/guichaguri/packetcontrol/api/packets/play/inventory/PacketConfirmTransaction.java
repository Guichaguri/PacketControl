package com.guichaguri.packetcontrol.api.packets.play.inventory;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.WindowHolder;

public interface PacketConfirmTransaction extends Packet, WindowHolder {

    short getTransactionId();

    void setTransactionId(short transactionId);

    boolean isAccepted();

    void setAccepted(boolean accepted);
}
