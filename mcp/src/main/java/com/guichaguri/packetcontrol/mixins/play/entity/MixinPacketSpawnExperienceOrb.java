package com.guichaguri.packetcontrol.mixins.play.entity;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.packets.play.entity.PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.SPacketSpawnExperienceOrb;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(SPacketSpawnExperienceOrb.class)
public abstract class MixinPacketSpawnExperienceOrb implements PacketSpawnExperienceOrb {

    @Shadow private int entityID;
    @Shadow private double posX;
    @Shadow private double posY;
    @Shadow private double posZ;
    @Shadow private int xpValue;

    @Override
    public int getEntityId() {
        return entityID;
    }

    @Override
    public void setEntityId(int id) {
        entityID = id;
    }

    @Override
    public int getExperience() {
        return xpValue;
    }

    @Override
    public void setExperience(int value) {
        xpValue = value;
    }

    @Override
    public Vector3d getPosition() {
        return new Vector3d(posX, posY, posZ);
    }

    @Override
    public void setPosition(Vector3d position) {
        this.posX = position.getX();
        this.posY = position.getY();
        this.posZ = position.getZ();
    }

}
