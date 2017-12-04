package com.guichaguri.packetcontrol.mcp.play.client;

import com.guichaguri.packetcontrol.api.packets.play.client.PacketWindowClose;
import net.minecraft.network.play.client.CPacketCloseWindow;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.item.inventory.util.ContainerUtil;

@Mixin(CPacketCloseWindow.class)
public abstract class MixinPacketWindowClose implements PacketWindowClose {

    @Shadow private int windowId;

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
}
