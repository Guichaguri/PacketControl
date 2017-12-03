package com.guichaguri.packetcontrol.api.util;

import org.spongepowered.api.entity.Entity;

/**
 * Holds an entity id
 *
 * @author Guichaguri
 */
public interface EntityHolder {

    /**
     * Gets the entity id
     *
     * @return The numeric id
     */
    int getEntityId();

    /**
     * Sets the entity id
     *
     * @param id The numeric id
     */
    void setEntityId(int id);

    /**
     * Sets the entity id from an existing entity
     *
     * @param entity The entity
     */
    void setEntityId(Entity entity);

}
