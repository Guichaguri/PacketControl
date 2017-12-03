package com.guichaguri.packetcontrol.api.packets.play.client;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.WindowHolder;
import org.spongepowered.api.item.inventory.ItemStack;

public interface PacketClickWindow extends Packet, WindowHolder {

    int getSlotPostion();

    void setSlotPostion(int slotPostion);

    int getButtonClicked();

    void setButtonClicked(int buttonClicked);

    short getTransactionId();

    void setTransactionId(short transactionId);

    ItemStack getClickedItem();

    void setClickedItem(ItemStack clickedItem);

    /* TODO
    ClickType getMode();

    void setMode(ClickType mode);
    */
}
