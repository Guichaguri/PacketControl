package com.guichaguri.packetcontrol.api.packets.play.inventory;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.WindowHolder;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.List;

public interface PacketWindowItems extends Packet, WindowHolder {

    List<ItemStack> getItems();

    void setItems(List<ItemStack> items);

}
