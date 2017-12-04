package com.guichaguri.packetcontrol.mcp.mixins.play.client;

import com.flowpowered.math.vector.Vector3i;
import com.guichaguri.packetcontrol.api.packets.play.client.PacketUpdateSign;
import java.util.Arrays;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.util.VecHelper;

/**
 * @author Guichaguri
 */
@Mixin(CPacketUpdateSign.class)
public abstract class MixinPacketUpdateSign implements PacketUpdateSign {

    @Shadow private BlockPos pos;
    @Shadow private String[] lines;

    @Override
    public String[] getLines() {
        return lines;
    }

    @Override
    public void setLines(String ... lineArray) {
        // This enforces the array to always keep the same length instead of hardcoding the number of lines
        if(lineArray.length != lines.length) {
            this.lines = Arrays.copyOf(lineArray, lines.length);
        } else {
            this.lines = lineArray;
        }

        // Make sure the array doesn't have null values
        for(int i = 0; i < lines.length; i++) {
            if(lines[i] == null) lines[i] = "";
        }
    }

    @Override
    public Vector3i getPosition() {
        return VecHelper.toVector3i(pos);
    }

    @Override
    public void setPosition(Vector3i position) {
        pos = VecHelper.toBlockPos(position);
    }
}
