package com.guichaguri.packetcontrol.mixins.play.player;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.packets.play.player.PacketVehiclePosition;
import net.minecraft.network.play.server.SPacketMoveVehicle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketMoveVehicle.class)
public abstract class MixinPacketVehiclePosition implements PacketVehiclePosition {

    @Shadow private double x;
    @Shadow private double y;
    @Shadow private double z;
    @Shadow private float yaw;
    @Shadow private float pitch;

    @Override
    public Vector3d getRotation() {
        return new Vector3d(pitch, yaw, 0);
    }

    @Override
    public void setRotation(Vector3d rotation) {
        pitch = (float)rotation.getX();
        yaw = (float)rotation.getY();
    }

    @Override
    public Vector3d getPosition() {
        return new Vector3d(x, y, z);
    }

    @Override
    public void setPosition(Vector3d position) {
        x = position.getX();
        y = position.getY();
        z = position.getZ();
    }
}
