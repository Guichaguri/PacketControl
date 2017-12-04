package com.guichaguri.packetcontrol.mcp.mixins.play.world;

import com.flowpowered.math.vector.Vector3i;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketOpenSignEditor;
import net.minecraft.network.play.server.SPacketSignEditorOpen;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.util.VecHelper;

/**
 * @author Guichaguri
 */
@Mixin(SPacketSignEditorOpen.class)
public abstract class MixinPacketOpenSignEditor implements PacketOpenSignEditor {

    @Shadow private BlockPos signPosition;

    @Override
    public Vector3i getPosition() {
        return VecHelper.toVector3i(signPosition);
    }

    @Override
    public void setPosition(Vector3i position) {
        signPosition = VecHelper.toBlockPos(position);
    }

}
