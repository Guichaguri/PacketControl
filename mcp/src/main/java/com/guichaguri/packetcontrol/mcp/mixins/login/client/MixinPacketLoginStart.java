package com.guichaguri.packetcontrol.mcp.mixins.login.client;

import com.guichaguri.packetcontrol.api.packets.login.client.PacketLoginStart;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.login.client.CPacketLoginStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(CPacketLoginStart.class)
public abstract class MixinPacketLoginStart implements PacketLoginStart {

    @Shadow private GameProfile profile;

    @Override
    public String getName() {
        return profile.getName();
    }

    @Override
    public void setName(String username) {
        profile = new GameProfile(null, username);
    }

}
