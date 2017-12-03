package com.guichaguri.packetcontrol.mixins.play.entity;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.packets.play.entity.PacketSpawnObject;
import com.guichaguri.packetcontrol.plugin.util.PacketUtils;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.entity.item.EntityMinecart.Type;
import net.minecraft.network.play.server.SPacketSpawnObject;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.common.entity.SpongeEntityType;

/**
 * @author Guichaguri
 */
@Mixin(SPacketSpawnObject.class)
public abstract class MixinPacketSpawnObject implements PacketSpawnObject {

    @Shadow private int entityId;
    @Shadow private UUID uniqueId;
    @Shadow private double x;
    @Shadow private double y;
    @Shadow private double z;
    @Shadow private int speedX;
    @Shadow private int speedY;
    @Shadow private int speedZ;
    @Shadow private int pitch;
    @Shadow private int yaw;
    @Shadow private int type;
    @Shadow private int data;

    private EntityType spongeType = SpongeEntityType.UNKNOWN;

    @Inject(method = "<init>(Lnet/minecraft/entity/Entity;II)V", at = @At("RETURN"))
    public void onConstructed(net.minecraft.entity.Entity entity, int type, int data, CallbackInfo ci) {
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

        updateType(null, type);
    }

    @Override
    public void setTypeAndData(Entity entity) {
        this.spongeType = entity.getType();

        updateType(entity, entity.getType());
    }

    @Override
    public int getTypeId() {
        return type;
    }

    @Override
    public void setTypeId(int type) {
        this.type = type;
    }

    private void updateType(@Nullable Entity entity, EntityType type) {
        if(type == EntityTypes.BOAT) {
            this.type = 1;
        } else if(type == EntityTypes.ITEM) {
            this.type = 2;
        } else if(type == EntityTypes.AREA_EFFECT_CLOUD) {
            this.type = 3;
        } else if(type == EntityTypes.RIDEABLE_MINECART) {
            this.type = 10;
            this.data = Type.RIDEABLE.getId();
        } else if(type == EntityTypes.CHESTED_MINECART) {
            this.type = 10;
            this.data = Type.CHEST.getId();
        } else if(type == EntityTypes.FURNACE_MINECART) {
            this.type = 10;
            this.data = Type.FURNACE.getId();
        } else if(type == EntityTypes.TNT_MINECART) {
            this.type = 10;
            this.data = Type.TNT.getId();
        } else if(type == EntityTypes.MOB_SPAWNER_MINECART) {
            this.type = 10;
            this.data = Type.SPAWNER.getId();
        } else if(type == EntityTypes.HOPPER_MINECART) {
            this.type = 10;
            this.data = Type.HOPPER.getId();
        } else if(type == EntityTypes.COMMANDBLOCK_MINECART) {
            this.type = 10;
            this.data = Type.COMMAND_BLOCK.getId();
        } else if(type == EntityTypes.PRIMED_TNT) {
            this.type = 50;
        } else if(type == EntityTypes.ENDER_CRYSTAL) {
            this.type = 51;
        } else if(type == EntityTypes.TIPPED_ARROW) {
            this.type = 60;
        } else if(type == EntityTypes.SNOWBALL) {
            this.type = 61;
        } else if(type == EntityTypes.EGG) {
            this.type = 62;
        } else if(type == EntityTypes.FIREBALL) {
            this.type = 63;
        } else if(type == EntityTypes.SMALL_FIREBALL) {
            this.type = 64;
        } else if(type == EntityTypes.ENDER_PEARL) {
            this.type = 65;
        } else if(type == EntityTypes.WITHER_SKULL) {
            this.type = 66;
        } else if(type == EntityTypes.SHULKER_BULLET) {
            this.type = 67;
        } else if(type == EntityTypes.LLAMA_SPIT) {
            this.type = 68;
        } else if(type == EntityTypes.FALLING_BLOCK) {
            this.type = 70;
        } else if(type == EntityTypes.ITEM_FRAME) {
            this.type = 71;
        } else if(type == EntityTypes.EYE_OF_ENDER) {
            this.type = 72;
        } else if(type == EntityTypes.SPLASH_POTION) {
            this.type = 73;
        } else if(type == EntityTypes.THROWN_EXP_BOTTLE) {
            this.type = 75;
        } else if(type == EntityTypes.FIREWORK) {
            this.type = 76;
        } else if(type == EntityTypes.LEASH_HITCH) {
            this.type = 77;
        } else if(type == EntityTypes.ARMOR_STAND) {
            this.type = 78;
        } else if(type == EntityTypes.EVOCATION_FANGS) {
            this.type = 79;
        } else if(type == EntityTypes.FISHING_HOOK) {
            this.type = 90;
        } else if(type == EntityTypes.SPECTRAL_ARROW) {
            this.type = 91;
        } else if(type == EntityTypes.DRAGON_FIREBALL) {
            this.type = 93;
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

    @Override
    public Vector3d getVelocity() {
        return new Vector3d(PacketUtils.decompileVelocity(speedX),
                            PacketUtils.decompileVelocity(speedY),
                            PacketUtils.decompileVelocity(speedZ));
    }

    @Override
    public void setVelocity(Vector3d velocity) {
        this.speedX = PacketUtils.compileVelocity(velocity.getX());
        this.speedY = PacketUtils.compileVelocity(velocity.getY());
        this.speedZ = PacketUtils.compileVelocity(velocity.getZ());
    }

    @Override
    public Vector3d getRotation() {
        return new Vector3d(PacketUtils.decompileRotation(pitch), PacketUtils.decompileRotation(yaw), 0);
    }

    @Override
    public void setRotation(Vector3d rotation) {
        pitch = PacketUtils.compileRotation(rotation.getX());
        yaw = PacketUtils.compileRotation(rotation.getY());
    }

    @Override
    public int getData() {
        return data;
    }

    @Override
    public void setData(int data) {
        this.data = data;
    }

}
