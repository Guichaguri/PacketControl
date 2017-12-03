package com.guichaguri.packetcontrol.mixins.play.player;

import com.guichaguri.packetcontrol.api.packets.play.player.PacketPlayerAbilities;
import net.minecraft.network.play.server.SPacketPlayerAbilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketPlayerAbilities.class)
public abstract class MixinPacketPlayerAbilities implements PacketPlayerAbilities {

    @Shadow private boolean invulnerable;
    @Shadow private boolean flying;
    @Shadow private boolean allowFlying;
    @Shadow private boolean creativeMode;
    @Shadow private float flySpeed;
    @Shadow private float walkSpeed;

    @Override
    public boolean isInvulnerable() {
        return invulnerable;
    }

    @Override
    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    @Override
    public boolean isCreativeMode() {
        return creativeMode;
    }

    @Override
    public void setCreativeMode(boolean creativeMode) {
        this.creativeMode = creativeMode;
    }

    @Override
    public boolean isFlying() {
        return flying;
    }

    @Override
    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    @Override
    public boolean getAllowFlight() {
        return allowFlying;
    }

    @Override
    public void setAllowFlight(boolean allowFlight) {
        this.allowFlying = allowFlight;
    }

    @Override
    public float getFlySpeed() {
        return flySpeed;
    }

    @Override
    public void setFlySpeed(float speed) {
        flySpeed = speed;
    }

    @Override
    public float getWalkSpeed() {
        return walkSpeed;
    }

    @Override
    public void setWalkSpeed(float speed) {
        walkSpeed = speed;
    }
}
