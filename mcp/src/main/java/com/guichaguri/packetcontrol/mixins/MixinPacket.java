package com.guichaguri.packetcontrol.mixins;

import com.guichaguri.packetcontrol.api.PacketType;
import com.guichaguri.packetcontrol.plugin.PacketRegistryModule;
import net.minecraft.network.Packet;
import org.spongepowered.asm.mixin.Mixin;

/**
 * @author Guichaguri
 */
@Mixin(Packet.class)
public interface MixinPacket extends com.guichaguri.packetcontrol.api.Packet {

    @SuppressWarnings("unchecked")
    @Override
    default PacketType getType() {
        return PacketRegistryModule.getInstance().getForClass((Class<? extends Packet>)getClass());
    }

}
