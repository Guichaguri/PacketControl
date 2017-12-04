package com.guichaguri.packetcontrol.mcp.mixins.play.entity;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.EntityMetadata;
import com.guichaguri.packetcontrol.api.packets.play.entity.PacketSpawnMob;
import com.guichaguri.packetcontrol.mcp.plugin.util.PacketUtils;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.EntityDataManager.DataEntry;
import net.minecraft.network.play.server.SPacketSpawnMob;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.common.entity.SpongeEntityType;

/**
 * @author Guichaguri
 */
@Mixin(SPacketSpawnMob.class)
public abstract class MixinPacketSpawnMob implements PacketSpawnMob {

    @Shadow private int entityId;
    @Shadow private UUID uniqueId;
    @Shadow private int type;
    @Shadow private double x;
    @Shadow private double y;
    @Shadow private double z;
    @Shadow private int velocityX;
    @Shadow private int velocityY;
    @Shadow private int velocityZ;
    @Shadow private byte yaw;
    @Shadow private byte pitch;
    @Shadow private byte headPitch;
    @Shadow private EntityDataManager dataManager;
    @Shadow private List<DataEntry<?>> dataManagerEntries;

    private EntityType spongeType = SpongeEntityType.UNKNOWN;

    @Inject(method = "<init>(Lnet/minecraft/entity/EntityLivingBase;)V", at = @At("RETURN"))
    public void onConstructed(EntityLivingBase entity, CallbackInfo ci) {
        spongeType = ((Entity)entity).getType();
    }

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
    public EntityType getEntityType() {
        return spongeType;
    }

    @Override
    public void setEntityType(EntityType type) {
        this.spongeType = type;
        this.type = PacketUtils.getTypeId(type);
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
    public Vector3d getVelocity() {
        return new Vector3d(PacketUtils.decompileVelocity(velocityX),
                            PacketUtils.decompileVelocity(velocityY),
                            PacketUtils.decompileVelocity(velocityZ));
    }

    @Override
    public void setVelocity(Vector3d velocity) {
        this.velocityX = PacketUtils.compileVelocity(velocity.getX());
        this.velocityY = PacketUtils.compileVelocity(velocity.getY());
        this.velocityZ = PacketUtils.compileVelocity(velocity.getZ());
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
    public Vector3d getHeadRotation() {
        return new Vector3d(PacketUtils.decompileRotation(headPitch), 0, 0);
    }

    @Override
    public void setHeadRotation(Vector3d rotation) {
        headPitch = PacketUtils.compileRotationByte(rotation.getX());
    }

    @Override
    public EntityMetadata getMetadata() {
        return (EntityMetadata)dataManager;
    }

    @Override
    public void setMetadata(EntityMetadata metadata) {
        dataManager = (EntityDataManager)metadata;
    }
}
