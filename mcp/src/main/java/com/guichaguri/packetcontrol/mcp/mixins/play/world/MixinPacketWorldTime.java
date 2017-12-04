package com.guichaguri.packetcontrol.mcp.mixins.play.world;

import com.guichaguri.packetcontrol.api.packets.play.world.PacketWorldTime;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketTimeUpdate.class)
public abstract class MixinPacketWorldTime implements PacketWorldTime {

    @Shadow private long totalWorldTime;
    @Shadow private long worldTime;

    public MixinPacketWorldTime(WorldProperties properties) {
        this.totalWorldTime = properties.getTotalTime();
        this.worldTime = properties.getWorldTime();
    }

    @Override
    public long getTotalTime() {
        return totalWorldTime;
    }

    @Override
    public void setTotalTime(long time) {
        totalWorldTime = time;
    }

    @Override
    public long getWorldTime() {
        return worldTime;
    }

    @Override
    public void setWorldTime(long time) {
        worldTime = time;
    }

}
