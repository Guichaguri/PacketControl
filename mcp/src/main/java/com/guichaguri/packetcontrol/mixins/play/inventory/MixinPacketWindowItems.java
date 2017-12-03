package com.guichaguri.packetcontrol.mixins.play.inventory;

import com.guichaguri.packetcontrol.api.packets.play.inventory.PacketWindowItems;
import net.minecraft.network.play.server.SPacketWindowItems;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.item.inventory.util.ContainerUtil;
import org.spongepowered.common.item.inventory.util.ItemStackUtil;

import java.util.List;
import java.util.stream.Collectors;

@Mixin(SPacketWindowItems.class)
public abstract class MixinPacketWindowItems implements PacketWindowItems {

    @Shadow private int windowId;
    @Shadow private List<net.minecraft.item.ItemStack> itemStacks;

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
    public List<ItemStack> getItems() {
        return itemStacks.stream().map(ItemStackUtil::fromNative).collect(Collectors.toList());
    }

    @Override
    public void setItems(List<ItemStack> items) {
        itemStacks = items.stream().map(ItemStackUtil::toNative).collect(Collectors.toList());
    }

}
