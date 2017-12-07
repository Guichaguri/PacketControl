package com.guichaguri.packetcontrol.mcp.mixins.login.server;

import com.guichaguri.packetcontrol.api.packets.login.server.PacketSetCompression;
import net.minecraft.network.login.server.SPacketEnableCompression;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketEnableCompression.class)
public abstract class MixinPacketSetCompression implements PacketSetCompression {

    @Shadow private int compressionThreshold;

    @Override
    public int getThreshold() {
        return compressionThreshold;
    }

    @Override
    public void setThreshold(int threshold) {
        this.compressionThreshold = threshold;
    }

}
