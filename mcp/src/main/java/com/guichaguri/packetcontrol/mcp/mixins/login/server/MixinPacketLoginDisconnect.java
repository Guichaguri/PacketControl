package com.guichaguri.packetcontrol.mcp.mixins.login.server;

import com.guichaguri.packetcontrol.api.packets.login.server.PacketLoginDisconnect;
import net.minecraft.network.login.server.SPacketDisconnect;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.api.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.text.SpongeTexts;

/**
 * @author Guichaguri
 */
@Mixin(SPacketDisconnect.class)
public abstract class MixinPacketLoginDisconnect implements PacketLoginDisconnect {

    @Shadow private ITextComponent reason;

    @Override
    public Text getReason() {
        return SpongeTexts.toText(reason);
    }

    @Override
    public void setReason(Text text) {
        reason = SpongeTexts.toComponent(text);
    }

}
