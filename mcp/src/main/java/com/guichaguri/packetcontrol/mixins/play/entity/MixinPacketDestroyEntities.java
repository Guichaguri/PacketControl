package com.guichaguri.packetcontrol.mixins.play.entity;

import com.guichaguri.packetcontrol.api.packets.play.entity.PacketDestroyEntities;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketDestroyEntities.class)
public abstract class MixinPacketDestroyEntities implements PacketDestroyEntities {

    @Shadow private int[] entityIDs;

    @Override
    public void setEntityIds(int[] ids) {
        entityIDs = ids;
    }

    @Override
    public int[] getEntityIds() {
        return entityIDs;
    }
}
