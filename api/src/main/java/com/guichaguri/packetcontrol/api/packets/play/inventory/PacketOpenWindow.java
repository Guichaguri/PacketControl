package com.guichaguri.packetcontrol.api.packets.play.inventory;

import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.util.EntityHolder;
import com.guichaguri.packetcontrol.api.util.WindowHolder;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.text.Text;

public interface PacketOpenWindow extends Packet, EntityHolder, WindowHolder {

    InventoryArchetype getInventoryType();

    void setInventoryType(InventoryArchetype inventoryType);

    Text getTitle();

    void setTitle(Text title);

    int getSlotCount();

    void setSlotCount(int slotCount);
}
