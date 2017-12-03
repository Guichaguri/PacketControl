package com.guichaguri.packetcontrol.api.packets.play.inventory;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.WindowHolder;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.Slot;

public interface PacketSetSlot extends Packet, WindowHolder {

    int getSlot();

    void setSlot(int slot);

    void setSlotAndItem(Slot slot);

    ItemStack getItem();

    void setItem(ItemStack item);
}
