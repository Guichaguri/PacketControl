package com.guichaguri.packetcontrol.mixins.play.player;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.EntityMetadata;
import com.guichaguri.packetcontrol.api.packets.play.player.PacketSpawnPlayer;
import com.guichaguri.packetcontrol.plugin.util.PacketUtils;
import java.util.List;
import java.util.UUID;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.EntityDataManager.DataEntry;
import net.minecraft.network.play.server.SPacketSpawnPlayer;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketSpawnPlayer.class)
public abstract class MixinPacketSpawnPlayer implements PacketSpawnPlayer {

    @Shadow private int entityId;
    @Shadow private UUID uniqueId;
    @Shadow private double x;
    @Shadow private double y;
    @Shadow private double z;
    @Shadow private byte yaw;
    @Shadow private byte pitch;
    @Shadow private EntityDataManager watcher;
    @Shadow private List<DataEntry<?>> dataManagerEntries;

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public void setEntityId(int id) {
        entityId = id;
    }

    @Override
    public void setEntityId(Entity entity) {
        entityId = ((net.minecraft.entity.Entity)entity).getEntityId();
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
    public Vector3d getPosition() {
        return new Vector3d(x, y, z);
    }

    @Override
    public void setPosition(Vector3d position) {
        this.x = position.getX();
        this.y = position.getY();
        this.z = position.getZ();
    }

    @Override
    public Vector3d getRotation() {
        return new Vector3d(PacketUtils.decompileRotation(pitch), PacketUtils.decompileRotation(yaw), 0);
    }

    @Override
    public void setRotation(Vector3d rotation) {
        pitch = PacketUtils.compileRotationByte(rotation.getX());
        yaw = PacketUtils.compileRotationByte(rotation.getY());
    }

    @Override
    public EntityMetadata getMetadata() {
        return (EntityMetadata)watcher;
    }

    @Override
    public void setMetadata(EntityMetadata metadata) {
        watcher = (EntityDataManager)metadata;
    }
}
