package com.guichaguri.packetcontrol.api;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.util.annotation.CatalogedBy;

/**
 * @author Guichaguri
 */
@CatalogedBy(PacketTypes.class)
public interface PacketType<T extends Packet, A> extends CatalogType {

    Class<T> getPacketClass();

    PacketSide getSide();

}
