package com.guichaguri.packetcontrol.mcp.play.client;

import com.guichaguri.packetcontrol.api.packets.play.client.PacketClickWindow;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketClickWindow;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.item.inventory.util.ContainerUtil;
import org.spongepowered.common.item.inventory.util.ItemStackUtil;

@Mixin(CPacketClickWindow.class)
public abstract class MixinPacketClickWindow implements PacketClickWindow {

    @Shadow private int windowId;
    @Shadow private int slotId;
    @Shadow private int packedClickData;
    @Shadow private short actionNumber;
    @Shadow private ItemStack clickedItem;
    @Shadow private ClickType mode;

    @Override
    public int getWindowId() {
        return this.windowId;
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
    public int getSlotPostion() {
        return slotId;
    }

    @Override
    public void setSlotPostion(int slotId) {
        this.slotId = slotId;
    }

    @Override
    public int getButtonClicked() {
        return packedClickData;
    }

    @Override
    public void setButtonClicked(int buttonClicked) {
        packedClickData = buttonClicked;
    }

    @Override
    public short getTransactionId() {
        return actionNumber;
    }

    @Override
    public void setTransactionId(short transactionId) {
        actionNumber = transactionId;
    }

    @Override
    public org.spongepowered.api.item.inventory.ItemStack getClickedItem() {
        return ItemStackUtil.fromNative(clickedItem);
    }

    @Override
    public void setClickedItem(org.spongepowered.api.item.inventory.ItemStack clickedItem) {
        this.clickedItem = ItemStackUtil.toNative(clickedItem);
    }
}
