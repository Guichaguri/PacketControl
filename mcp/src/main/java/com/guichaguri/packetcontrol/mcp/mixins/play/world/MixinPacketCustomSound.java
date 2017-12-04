package com.guichaguri.packetcontrol.mcp.mixins.play.world;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketCustomSound;
import net.minecraft.network.play.server.SPacketCustomSound;
import org.spongepowered.api.effect.sound.SoundCategory;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketCustomSound.class)
public abstract class MixinPacketCustomSound implements PacketCustomSound {

    @Shadow private String soundName;
    @Shadow private net.minecraft.util.SoundCategory category;
    @Shadow private int x;
    @Shadow private int y = Integer.MAX_VALUE;
    @Shadow private int z;
    @Shadow private float volume;
    @Shadow private float pitch;

    @Override
    public SoundType getSound() {
        return SoundType.of(soundName);
    }

    @Override
    public void setSound(SoundType type) {
        soundName = type.getId();
    }

    @Override
    public SoundCategory getCategory() {
        return (SoundCategory)(Object)category;
    }

    @Override
    public void setCategory(SoundCategory cat) {
        category = (net.minecraft.util.SoundCategory)(Object)cat;
    }

    @Override
    public float getVolume() {
        return volume;
    }

    @Override
    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public float getPitch() {
        return pitch;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public Vector3d getPosition() {
        return new Vector3d(x / 8D, y / 8D, z / 8D);
    }

    @Override
    public void setPosition(Vector3d position) {
        this.x = (int)(position.getX() * 8);
        this.y = (int)(position.getY() * 8);
        this.z = (int)(position.getZ() * 8);
    }
}
