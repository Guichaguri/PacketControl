package com.guichaguri.packetcontrol.api;

import org.spongepowered.api.entity.Entity;

/**
 * An entity metadata holds the data that an entity sends to a client.
 *
 * Although the data can't be directly manipulated, you can create new instances from {@link Entity} objects.
 *
 * When the metadata is synchronized with an entity, changing any entity property also changes the metadata.
 *
 * @see com.guichaguri.packetcontrol.api.PacketService#getMetadata(Entity)
 * @see com.guichaguri.packetcontrol.api.PacketService#createSnapshot(Entity)
 * @see com.guichaguri.packetcontrol.api.PacketService#createSnapshot(EntityMetadata)
 */
public interface EntityMetadata {

    /**
     * Gets the entity that holds this metadata
     *
     * @return The entity
     */
    Entity getEntity();

}
