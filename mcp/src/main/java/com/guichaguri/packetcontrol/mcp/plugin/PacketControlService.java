package com.guichaguri.packetcontrol.mcp.plugin;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.PacketService;
import com.guichaguri.packetcontrol.api.PacketSide;
import com.guichaguri.packetcontrol.api.PacketType;
import com.guichaguri.packetcontrol.api.EntityMetadata;
import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.PacketHandler;
import java.util.Collection;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.EntityDataManager.DataEntry;
import net.minecraft.world.WorldServer;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.network.PlayerConnection;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

/**
 * @author Guichaguri
 */
public class PacketControlService implements PacketService {

    @SuppressWarnings("unchecked")
    @Override
    public <P extends Packet> void registerHandler(PacketType<P, ?> type, PacketHandler<P> handler) {
        if(!(type instanceof PacketControlType)) {
            throw new RuntimeException("The packet type is not a PacketControlType");
        }

        ((PacketControlType)type).registerHandler(handler);
    }

    @Override
    public <P extends Packet> P createPacket(PacketType<P, ?> type) {
        if(type.getSide() == PacketSide.SERVERBOUND) {
            throw new IllegalArgumentException("You can't initialize a serverbound packet");
        }

        try {
            return ConstructorUtils.invokeConstructor(type.getPacketClass());
        } catch(Exception ex) {
            throw new RuntimeException("There was an issue constructing " + type.getName(), ex);
        }
    }

    @Override
    public <P extends Packet, A> P createPacket(PacketType<P, A> type, A param) {
        if(type.getSide() == PacketSide.SERVERBOUND) {
            throw new IllegalArgumentException("You can't initialize a serverbound packet");
        }

        PacketFactory<P, A> factory = ((PacketControlType<P, A>)type).getFactory();

        if(factory != null) {
            return factory.create(param);
        }

        try {
            return ConstructorUtils.invokeConstructor(type.getPacketClass(), param);
        } catch(Exception ex) {
            throw new RuntimeException("There was an issue constructing " + type.getName(), ex);
        }
    }

    @Override
    public EntityMetadata getMetadata(Entity entity) {
        return (EntityMetadata)((net.minecraft.entity.Entity)entity).getDataManager();
    }

    @Override
    public EntityMetadata createSnapshot(EntityMetadata metadata) {
        EntityDataManager manager = new EntityDataManager((net.minecraft.entity.Entity)metadata.getEntity());
        List<DataEntry<?>> entries = ((EntityDataManager)metadata).getAll();

        if(entries != null) {
            for(DataEntry<?> entry : entries) {
                register(manager, entry);
            }
        }

        return (EntityMetadata)manager;
    }

    private <T> void register(EntityDataManager manager, DataEntry<T> entry) {
        manager.register(entry.getKey(), entry.getValue());
    }

    @Override
    public void sendPacket(Packet packet, PlayerConnection connection) {
        if(connection instanceof NetworkManager) {
            ((NetworkManager)connection).sendPacket((net.minecraft.network.Packet)packet);
        } else if(connection instanceof NetHandlerPlayServer) {
            ((NetHandlerPlayServer)connection).sendPacket((net.minecraft.network.Packet)packet);
        } else {
            sendPacket(packet, connection.getPlayer());
        }
    }

    @Override
    public void sendPacket(Packet packet, Player player) {
        ((EntityPlayerMP)player).connection.sendPacket((net.minecraft.network.Packet)packet);
    }

    @Override
    public void sendPacket(Packet packet, Player ... players) {
        net.minecraft.network.Packet mcPacket = (net.minecraft.network.Packet)packet;

        for(Player player : players) {
            ((EntityPlayerMP)player).connection.sendPacket(mcPacket);
        }
    }

    @Override
    public void sendPacket(Packet packet, Collection<Player> players) {
        net.minecraft.network.Packet mcPacket = (net.minecraft.network.Packet)packet;

        for(Player player : players) {
            ((EntityPlayerMP)player).connection.sendPacket(mcPacket);
        }
    }

    @Override
    public void sendPacketToTracking(Packet packet, Entity tracked) {
        net.minecraft.entity.Entity mcEntity = (net.minecraft.entity.Entity)tracked;
        WorldServer world = (WorldServer)mcEntity.getEntityWorld();

        world.getEntityTracker().sendToTracking(mcEntity, (net.minecraft.network.Packet)packet);
    }

    @Override
    public void sendPacketWithinDistance(Packet packet, Location<World> location, double distance) {
        net.minecraft.network.Packet mcPacket = (net.minecraft.network.Packet)packet;
        Vector3d vec = location.getPosition();
        double distanceSquared = distance * distance;

        for(Player player : location.getExtent().getPlayers()) {
            double d = player.getLocation().getPosition().distanceSquared(vec);
            if(d > distanceSquared) continue;

            ((EntityPlayerMP)player).connection.sendPacket(mcPacket);
        }
    }

}
