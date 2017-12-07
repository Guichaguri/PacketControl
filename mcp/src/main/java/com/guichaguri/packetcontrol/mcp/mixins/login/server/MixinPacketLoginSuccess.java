package com.guichaguri.packetcontrol.mcp.mixins.login.server;

import com.guichaguri.packetcontrol.api.packets.login.server.PacketLoginSuccess;
import net.minecraft.network.login.server.SPacketLoginSuccess;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketLoginSuccess.class)
public abstract class MixinPacketLoginSuccess implements PacketLoginSuccess {

    @Shadow private com.mojang.authlib.GameProfile profile;

    @Override
    public GameProfile getProfile() {
        return (GameProfile)profile;
    }

    @Override
    public void setProfile(GameProfile profile) {
        this.profile = (com.mojang.authlib.GameProfile)profile;
    }

}
