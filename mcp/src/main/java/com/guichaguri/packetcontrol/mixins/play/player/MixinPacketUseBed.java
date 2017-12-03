package com.guichaguri.packetcontrol.mixins.play.player;

import com.flowpowered.math.vector.Vector3i;
import com.guichaguri.packetcontrol.api.packets.play.player.PacketUseBed;
import net.minecraft.network.play.server.SPacketUseBed;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.util.VecHelper;

/**
 * @author Guichaguri
 */
@Mixin(SPacketUseBed.class)
public abstract class MixinPacketUseBed implements PacketUseBed {

    @Shadow private int playerID;
    @Shadow private BlockPos bedPos;

    @Override
    public int getEntityId() {
        return playerID;
    }

    @Override
    public void setEntityId(int id) {
        playerID = id;
    }

    @Override
    public void setEntityId(Entity entity) {
        playerID = ((net.minecraft.entity.Entity)entity).getEntityId();
    }

    @Override
    public Vector3i getPosition() {
        return VecHelper.toVector3i(bedPos);
    }

    @Override
    public void setPosition(Vector3i position) {
        bedPos = VecHelper.toBlockPos(position);
    }
}
