package com.guichaguri.packetcontrol.mixins.play.inventory;

import com.guichaguri.packetcontrol.api.packets.play.inventory.PacketSetSlot;
import net.minecraft.network.play.server.SPacketSetSlot;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.item.inventory.util.ContainerUtil;
import org.spongepowered.common.item.inventory.util.ItemStackUtil;

import java.util.Optional;

@Mixin(SPacketSetSlot.class)
public abstract class MixinPacketSetSlot implements PacketSetSlot {

    @Shadow private int windowId;
    @Shadow private int slot;
    @Shadow private net.minecraft.item.ItemStack item;

    @Override
    public int getWindowId() {
        return windowId;
    }

    @Override
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    @Override
    public void setWindowId(Container container) {
        windowId = ContainerUtil.toNative(container).windowId;
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public ItemStack getItem() {
        return ItemStackUtil.fromNative(item);
    }

    @Override
    public void setItem(ItemStack item) {
        this.item = ItemStackUtil.toNative(item);
    }

    @Override
    public void setSlotAndItem(Slot slot) {
        net.minecraft.inventory.Slot nativeSlot = ((net.minecraft.inventory.Slot)(Object)slot);
        this.slot = nativeSlot.slotNumber;
        this.item = nativeSlot.getStack();
    }

}
