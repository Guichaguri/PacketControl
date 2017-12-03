package com.guichaguri.packetcontrol.api;

import java.util.Collection;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.network.PlayerConnection;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/**
 * The packet service allows you to create, send and intercept packets.
 *
 * Use {@link org.spongepowered.api.service.ServiceManager#provide(Class)} with this class to get an instance.
 *
 * @author Guichaguri
 */
public interface PacketService {

    /**
     * Registers a packet handler
     *
     * @param type The packet type to intercept
     * @param handler The handler
     */
    <P extends Packet> void registerHandler(PacketType<P, ?> type, PacketHandler<P> handler);

    /**
     * Creates a packet from a type
     *
     * @see PacketTypes
     * @param type The packet type
     * @return The created packet
     */
    <P extends Packet> P createPacket(PacketType<P, ?> type);

    /**
     * Creates a packet from a type with a parameter
     *
     * @see PacketTypes
     * @param type The packet type
     * @param param The parameter
     * @return The created packet
     */
    <P extends Packet, A> P createPacket(PacketType<P, A> type, A param);

    /**
     * Gets the metadata from an entity.
     *
     * This metadata is synchronized with the entity.
     *
     * @param entity The entity
     * @return The metadata
     */
    EntityMetadata getMetadata(Entity entity);

    /**
     * Creates an snapshot from an entity metadata.
     *
     * This snapshot is NOT synchronized with any entity.
     *
     * @param metadata The original metadata
     * @return The metadata snapshot
     */
    EntityMetadata createSnapshot(EntityMetadata metadata);

    /**
     * Creates a metadata snapshot from an entity.
     *
     * This snapshot is NOT synchronized with the entity.
     *
     * @param entity The entity
     * @return The metadata snapshot
     */
    default EntityMetadata createSnapshot(Entity entity) {
        return createSnapshot(getMetadata(entity));
    }

    /**
     * Sends a packet to a connection
     *
     * @param packet The packet
     * @param connection The connection
     */
    void sendPacket(Packet packet, PlayerConnection connection);

    /**
     * Sends a packet to an specific player
     *
     * @param packet The packet
     * @param player The player
     */
    void sendPacket(Packet packet, Player player);

    /**
     * Sends a packet to an array of players
     *
     * @param packet The packet
     * @param players An array of players
     */
    void sendPacket(Packet packet, Player ... players);

    /**
     * Sends a packet to a collection of players
     *
     * @param packet The packet
     * @param players The collection of players
     */
    void sendPacket(Packet packet, Collection<Player> players);

    /**
     * Sends a packet to all players that are tracking the entity.
     *
     * When an entity within the view distance of a player, the player is tracking it.
     *
     * @param packet The packet
     * @param tracked The tracked entity
     */
    void sendPacketToTracking(Packet packet, Entity tracked);

    /**
     * Sends a packet to all players within a distance
     *
     * @param packet The packet
     * @param location The location
     * @param distance The radius in blocks
     */
    void sendPacketWithinDistance(Packet packet, Location<World> location, double distance);

}
