package com.guichaguri.packetcontrol.mcp.mixins.login.client;

import com.guichaguri.packetcontrol.api.packets.login.client.PacketEncryptionResponse;
import net.minecraft.network.login.client.CPacketEncryptionResponse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(CPacketEncryptionResponse.class)
public abstract class MixinPacketEncryptionResponse implements PacketEncryptionResponse {

    @Shadow private byte[] secretKeyEncrypted = new byte[0];
    @Shadow private byte[] verifyTokenEncrypted = new byte[0];

    @Override
    public byte[] getEncryptedSecretKey() {
        return secretKeyEncrypted;
    }

    @Override
    public void setEncryptedSecretKey(byte[] secretKey) {
        secretKeyEncrypted = secretKey;
    }

    @Override
    public byte[] getEncryptedVerifyToken() {
        return verifyTokenEncrypted;
    }

    @Override
    public void setEncryptedVerifyToken(byte[] token) {
        verifyTokenEncrypted = token;
    }

}
