package com.guichaguri.packetcontrol.mcp.mixins.login.server;

import com.guichaguri.packetcontrol.api.packets.login.server.PacketEncryptionRequest;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import net.minecraft.network.login.server.SPacketEncryptionRequest;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketEncryptionRequest.class)
public abstract class MixinPacketEncryptionRequest implements PacketEncryptionRequest {

    @Shadow private String hashedServerId;
    @Shadow private PublicKey publicKey;
    @Shadow private byte[] verifyToken;

    @Override
    public String getServerId() {
        return hashedServerId;
    }

    @Override
    public void setServerId(String id) {
        hashedServerId = id;
    }

    @Override
    public byte[] getPublicKey() {
        return publicKey.getEncoded();
    }

    @Override
    public void setPublicKey(byte[] key) {
        try {
            publicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(key));
        } catch(Exception ex) {
            // Ignored
        }
    }

    @Override
    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public void setVerifyToken(byte[] token) {
        verifyToken = token;
    }

}
