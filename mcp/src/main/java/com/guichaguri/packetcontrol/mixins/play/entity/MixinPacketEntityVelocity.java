package com.guichaguri.packetcontrol.mixins.play.entity;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.packets.play.entity.PacketEntityVelocity;
import com.guichaguri.packetcontrol.plugin.util.PacketUtils;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SPacketEntityVelocity.class)
public abstract class MixinPacketEntityVelocity implements PacketEntityVelocity {

    @Shadow private int entityID;
    @Shadow private int motionX;
    @Shadow private int motionY;
    @Shadow private int motionZ;

    @Override
    public int getEntityId() {
        return entityID;
    }

    @Override
    public void setEntityId(int id) {
        this.entityID = id;
    }

    @Override
    public Vector3d getVelocity() {
        return new Vector3d(PacketUtils.decompileVelocity(motionX),
                            PacketUtils.decompileVelocity(motionY),
                            PacketUtils.decompileVelocity(motionZ));
    }

    @Override
    public void setVelocity(Vector3d velocity) {
        this.motionX = PacketUtils.compileVelocity(velocity.getX());
        this.motionY = PacketUtils.compileVelocity(velocity.getY());
        this.motionZ = PacketUtils.compileVelocity(velocity.getZ());
    }
}
