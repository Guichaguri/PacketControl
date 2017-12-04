package com.guichaguri.packetcontrol.mcp.play.inventory;

import com.guichaguri.packetcontrol.api.packets.play.inventory.PacketCloseWindow;
import net.minecraft.network.play.server.SPacketCloseWindow;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.item.inventory.util.ContainerUtil;

@Mixin(SPacketCloseWindow.class)
public abstract class MixinPacketCloseWindow implements PacketCloseWindow {

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
