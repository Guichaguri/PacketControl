package com.guichaguri.packetcontrol.mcp.mixins.play.world;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketGameSound;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundEvent;
import org.spongepowered.api.effect.sound.SoundCategory;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketSoundEffect.class)
public abstract class MixinPacketGameSound implements PacketGameSound {

    @Shadow private SoundEvent sound;
    @Shadow private net.minecraft.util.SoundCategory category;
    @Shadow private int posX;
    @Shadow private int posY;
    @Shadow private int posZ;
    @Shadow private float soundVolume;
    @Shadow private float soundPitch;

    @Override
    public SoundType getSound() {
        return (SoundType)sound;
    }

    @Override
    public void setSound(SoundType type) {
        if(type instanceof SoundEvent) {
            sound = (SoundEvent)type;
        } else {
            throw new IllegalArgumentException("PacketGameSound only accepts vanilla sound types. Use PacketCustomSound instead.");
        }
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
        return soundVolume;
    }

    @Override
    public void setVolume(float volume) {
        soundVolume = volume;
    }

    @Override
    public float getPitch() {
        return soundPitch;
    }

    @Override
    public void setPitch(float pitch) {
        soundPitch = pitch;
    }

    @Override
    public Vector3d getPosition() {
        return new Vector3d(posX / 8D, posY / 8D, posZ / 8D);
    }

    @Override
    public void setPosition(Vector3d position) {
        this.posX = (int)(position.getX() * 8);
        this.posY = (int)(position.getY() * 8);
        this.posZ = (int)(position.getZ() * 8);
    }
}
