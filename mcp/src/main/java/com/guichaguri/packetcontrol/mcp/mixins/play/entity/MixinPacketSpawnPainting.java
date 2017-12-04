package com.guichaguri.packetcontrol.mcp.mixins.play.entity;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.packets.play.entity.PacketSpawnPainting;
import java.util.UUID;
import net.minecraft.entity.item.EntityPainting.EnumArt;
import net.minecraft.network.play.server.SPacketSpawnPainting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.api.data.type.Art;
import org.spongepowered.api.data.type.Arts;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.util.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.registry.provider.DirectionFacingProvider;
import org.spongepowered.common.util.VecHelper;

/**
 * @author Guichaguri
 */
@Mixin(SPacketSpawnPainting.class)
public abstract class MixinPacketSpawnPainting implements PacketSpawnPainting {

    @Shadow private int entityID;
    @Shadow private UUID uniqueId;
    @Shadow private BlockPos position;
    @Shadow private EnumFacing facing;
    @Shadow private String title;

    @Override
    public int getEntityId() {
        return entityID;
    }

    @Override
    public void setEntityId(int id) {
        entityID = id;
    }

    @Override
    public void setEntityId(Entity entity) {
        entityID = ((net.minecraft.entity.Entity)entity).getEntityId();
    }

    @Override
    public UUID getUniqueId() {
        return uniqueId;
    }

    @Override
    public void setUniqueId(UUID uuid) {
        uniqueId = uuid;
    }

    @Override
    public Art getArt() {
        for(EnumArt art : EnumArt.values()) {
            if(art.title.equals(title)) return (Art)(Object)art;
        }
        return Arts.KEBAB;
    }

    @Override
    public void setArt(Art art) {
        title = ((EnumArt)(Object)art).title;
    }

    @Override
    public Direction getDirection() {
        return DirectionFacingProvider.getInstance().getKey(facing).orElse(Direction.NONE);
    }

    @Override
    public void setDirection(Direction direction) {
        facing = DirectionFacingProvider.getInstance().get(direction).orElse(facing);
    }

    @Override
    public Vector3d getPosition() {
        return VecHelper.toVector3d(position);
    }

    @Override
    public void setPosition(Vector3d pos) {
        position = VecHelper.toBlockPos(pos);
    }
}
