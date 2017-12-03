package com.guichaguri.packetcontrol.mixins.play.inventory;

import com.guichaguri.packetcontrol.api.packets.play.inventory.PacketConfirmTransaction;
import net.minecraft.network.play.server.SPacketConfirmTransaction;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.item.inventory.util.ContainerUtil;

@Mixin(SPacketConfirmTransaction.class)
public abstract class MixinPacketConfirmTransaction implements PacketConfirmTransaction {

    @Shadow private int windowId;
    @Shadow private short actionNumber;
    @Shadow private boolean accepted;

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
    public short getTransactionId() {
        return actionNumber;
    }

    @Override
    public void setTransactionId(short transactionId) {
        this.actionNumber = transactionId;
    }

    @Override
    public boolean isAccepted() {
        return accepted;
    }

    @Override
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
