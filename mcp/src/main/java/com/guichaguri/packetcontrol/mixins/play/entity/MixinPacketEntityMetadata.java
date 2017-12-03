package com.guichaguri.packetcontrol.mixins.play.entity;

import com.guichaguri.packetcontrol.api.EntityMetadata;
import com.guichaguri.packetcontrol.api.packets.play.entity.PacketEntityMetadata;
import java.util.List;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.EntityDataManager.DataEntry;
import net.minecraft.network.play.server.SPacketEntityMetadata;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Guichaguri
 */
@Mixin(SPacketEntityMetadata.class)
public abstract class MixinPacketEntityMetadata implements PacketEntityMetadata {

    @Shadow private int entityId;
    @Shadow private List<DataEntry<?>> dataManagerEntries;

    private EntityMetadata packetMetadata;

    @Inject(method = "<init>(ILnet/minecraft/network/datasync/EntityDataManager;Z)V", at = @At("RETURN"))
    public void onConstructed(int id, EntityDataManager manager, boolean sendAll, CallbackInfo ci) {
        packetMetadata = (EntityMetadata)manager;
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
    public EntityMetadata getMetadata() {
        return packetMetadata;
    }

    @Override
    public void setMetadata(EntityMetadata metadata) {
        dataManagerEntries = ((EntityDataManager)metadata).getAll();
        packetMetadata = metadata;
    }
}
