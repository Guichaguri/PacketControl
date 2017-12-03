package com.guichaguri.packetcontrol.mixins.play.entity;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.packets.play.entity.PacketSpawnGlobalEntity;
import com.guichaguri.packetcontrol.plugin.util.PacketUtils;
import net.minecraft.network.play.server.SPacketSpawnGlobalEntity;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Guichaguri
 */
@Mixin(SPacketSpawnGlobalEntity.class)
public abstract class MixinPacketSpawnGlobalEntity implements PacketSpawnGlobalEntity {

    @Shadow private int entityId;
    @Shadow private double x;
    @Shadow private double y;
    @Shadow private double z;
    @Shadow private int type;

    private EntityType spongeType = EntityTypes.LIGHTNING;

    @Inject(method = "<init>(Lnet/minecraft/entity/Entity;)V", at = @At("RETURN"))
    public void onConstructed(net.minecraft.entity.Entity entity, CallbackInfo ci) {
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
    public EntityType getEntityType() {
        return spongeType;
    }

    @Override
    public void setEntityType(EntityType type) {
        this.spongeType = type;

        if(type == EntityTypes.LIGHTNING) {
            this.type = 1;
        } else {
            this.type = PacketUtils.getTypeId(type);
        }
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

}
