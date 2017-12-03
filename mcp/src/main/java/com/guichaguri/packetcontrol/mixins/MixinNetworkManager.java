package com.guichaguri.packetcontrol.mixins;

import com.guichaguri.packetcontrol.api.PacketType;
import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.plugin.PacketControlType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetworkManager;
import org.spongepowered.api.network.PlayerConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Guichaguri
 */
@Mixin(NetworkManager.class)
public abstract class MixinNetworkManager extends SimpleChannelInboundHandler<net.minecraft.network.Packet<?>> {

    private PlayerConnection connection;

    @Inject(method = "setNetHandler(Lnet/minecraft/network/INetHandler;)V", at = @At("RETURN"))
    public void onNetHandlerUpdate(INetHandler handler, CallbackInfo info) {
        // We'll update the connection here, so we don't need to instanceof and cast on sendPacket, making it a bit faster
        // We'll also use the player connection from the INetHandler when possible, because that's what being used on Player#getConnection
        if(handler instanceof PlayerConnection) {
            connection = (PlayerConnection)handler;
        } else {
            connection = (PlayerConnection)this;
        }
    }

    @Inject(method = "sendPacket*", at = @At("HEAD"), cancellable = true)
    public void onSendPacket(net.minecraft.network.Packet<?> packet, CallbackInfo info) {
        handlePacket(packet, info);
    }

    @Inject(method = "channelRead0(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    public void onReceivePacket(ChannelHandlerContext ctx, net.minecraft.network.Packet<?> packet, CallbackInfo info) {
        handlePacket(packet, info);
    }

    @SuppressWarnings("unchecked")
    private void handlePacket(net.minecraft.network.Packet<?> packet, CallbackInfo info) {
        Packet p = (Packet)packet;
        PacketType type = p.getType();
        if(type == null) return;

        if(((PacketControlType)p.getType()).handlePacket(connection, p)) {
            info.cancel();
        }
    }

}
