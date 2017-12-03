package com.guichaguri.packetcontrol.plugin;

import com.guichaguri.packetcontrol.api.PacketType;
import com.guichaguri.packetcontrol.api.Packet;
import com.guichaguri.packetcontrol.api.PacketHandler;
import com.guichaguri.packetcontrol.api.PacketSide;
import java.util.Arrays;
import javax.annotation.Nullable;
import org.spongepowered.api.network.PlayerConnection;

/**
 * @author Guichaguri
 */
public class PacketControlType<T extends Packet, A> implements PacketType<T, A> {

    private final String id, name;
    private final PacketSide side;
    private final Class<? extends net.minecraft.network.Packet> clazz;
    private final PacketFactory<T, A> factory;

    private PacketHandler<T>[] handlers = null;

    public PacketControlType(String id, String name, PacketSide side, Class<? extends net.minecraft.network.Packet> clazz, PacketFactory<T, A> factory) {
        this.id = id;
        this.name = name;
        this.side = side;
        this.clazz = clazz;
        this.factory = factory;
    }

    @SuppressWarnings("unchecked")
    protected void registerHandler(PacketHandler<T> handler) {
        if(handlers == null) {
            handlers = new PacketHandler[]{handler};
        } else {
            int i = handlers.length;
            handlers = Arrays.copyOf(handlers, i + 1);
            handlers[i] = handler;
        }
    }

    public boolean handlePacket(PlayerConnection player, T packet) {
        if(handlers == null) return false;

        for(PacketHandler<T> handler : handlers) {
            try {
                if(handler.handle(player, packet)) {
                    return true;
                }
            } catch(Exception ex) {
                // If a plugin does something that throws an exception in handle, we'll catch it
                ex.printStackTrace();
            }
        }

        return false;
    }

    @Nullable
    public PacketFactory<T, A> getFactory() {
        return factory;
    }

    public Class<? extends net.minecraft.network.Packet> getOriginalClass() {
        return clazz;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<T> getPacketClass() {
        return (Class<T>)clazz;
    }

    @Override
    public PacketSide getSide() {
        return side;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;

        return getId().equals(((PacketControlType)obj).getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
