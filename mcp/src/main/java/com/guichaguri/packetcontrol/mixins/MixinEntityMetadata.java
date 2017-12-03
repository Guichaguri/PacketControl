package com.guichaguri.packetcontrol.mixins;

import com.guichaguri.packetcontrol.api.EntityMetadata;
import net.minecraft.network.datasync.EntityDataManager;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

/**
 * @author Guichaguri
 */
@Mixin(EntityDataManager.class)
public abstract class MixinEntityMetadata implements EntityMetadata {

    @Shadow @Final private net.minecraft.entity.Entity entity;

    @Override
    public Entity getEntity() {
        return (Entity)entity;
    }
}
