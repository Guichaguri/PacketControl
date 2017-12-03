package com.guichaguri.packetcontrol.plugin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.guichaguri.packetcontrol.api.PacketSide;
import com.guichaguri.packetcontrol.api.PacketType;
import com.guichaguri.packetcontrol.api.PacketTypes;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketUpdateSign;
import net.minecraft.network.play.client.CPacketVehicleMove;
import net.minecraft.network.play.server.*;
import org.spongepowered.api.registry.ExtraClassCatalogRegistryModule;
import org.spongepowered.api.registry.RegistrationPhase;
import org.spongepowered.api.registry.util.DelayedRegistration;
import org.spongepowered.api.registry.util.RegisterCatalog;
import org.spongepowered.common.registry.SpongeAdditionalCatalogRegistryModule;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Guichaguri
 */
public class PacketRegistryModule implements ExtraClassCatalogRegistryModule<PacketType, Packet>, SpongeAdditionalCatalogRegistryModule<PacketType> {

    public static PacketRegistryModule getInstance() {
        return PacketRegistryModule.Holder.INSTANCE;
    }

    @RegisterCatalog(PacketTypes.class)
    private final Map<String, PacketType> packetMappings = Maps.newHashMap();
    private final Map<Class<? extends Packet>, PacketType> packetTypeMappings = Maps.newHashMap();

    @Override
    public Optional<PacketType> getById(String id) {
        return Optional.ofNullable(packetMappings.get(checkNotNull(id).toLowerCase(Locale.ENGLISH)));
    }

    @Override
    public Collection<PacketType> getAll() {
        return ImmutableList.copyOf(packetMappings.values());
    }

    @DelayedRegistration(RegistrationPhase.PRE_INIT)
    @Override
    public void registerDefaults() {
        // Entity
        clientbound("destroy_entities", "Destroy Entities", SPacketDestroyEntities.class, PacketControlFactory::createDestroyEntities);
        clientbound("entity_metadata", "Entity Metadata", SPacketEntityMetadata.class, PacketControlFactory::createEntityMetadata);
        clientbound("spawn_experience_orb", "Spawn Experience Orb", SPacketSpawnExperienceOrb.class);
        clientbound("spawn_global_entity", "Spawn Global Entity", SPacketSpawnGlobalEntity.class);
        clientbound("spawn_mob", "Spawn Mob", SPacketSpawnMob.class);
        clientbound("spawn_object", "Spawn Object", SPacketSpawnObject.class, PacketControlFactory::createSpawnObject);
        clientbound("spawn_painting", "Spawn Painting", SPacketSpawnPainting.class);

        // Player
        clientbound("player_abilities", "Player Abilities", SPacketPlayerAbilities.class, PacketControlFactory::createPlayerAbilities);
        clientbound("spawn_player", "Spawn Player", SPacketSpawnPlayer.class);
        clientbound("use_bed", "Use Bed", SPacketUseBed.class, PacketControlFactory::createUseBed);
        clientbound("vehicle_position", "Vehicle Position", SPacketMoveVehicle.class);

        // World
        clientbound("custom_sound", "Custom Sound", SPacketCustomSound.class, PacketControlFactory::createCustomSound);
        clientbound("game_sound", "Game Sound", SPacketSoundEffect.class, PacketControlFactory::createGameSound);
        clientbound("open_sign_editor", "Open Sign Editor", SPacketSignEditorOpen.class, PacketControlFactory::createOpenSignEditor);
        clientbound("world_time", "World Time", SPacketTimeUpdate.class, PacketControlFactory::createWorldTime);

        // Client
        serverbound("move_vehicle", "Move Vehicle", CPacketVehicleMove.class);
        serverbound("update_sign", "Update Sign", CPacketUpdateSign.class);
    }

    @Override
    public boolean hasRegistrationFor(Class<? extends Packet> mappedClass) {
        return packetTypeMappings.containsKey(mappedClass);
    }

    @Override
    public PacketType getForClass(Class<? extends Packet> clazz) {
        return packetTypeMappings.get(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void registerAdditionalCatalog(PacketType extraCatalog) {
        packetMappings.put(extraCatalog.getId(), extraCatalog);
        packetTypeMappings.put(((PacketControlType)extraCatalog).getOriginalClass(), extraCatalog);
    }

    @Override
    public boolean allowsApiRegistration() {
        return true;
    }

    void serverbound(String id, String name, Class<? extends Packet> clazz) {
        registerPacketType(id, name, PacketSide.SERVERBOUND, clazz, null);
    }

    void clientbound(String id, String name, Class<? extends Packet> clazz) {
        registerPacketType(id, name, PacketSide.CLIENTBOUND, clazz, null);
    }

    <P extends com.guichaguri.packetcontrol.api.Packet, A>
    void clientbound(String id, String name, Class<? extends Packet> clazz, PacketFactory<P, A> factory) {
        registerPacketType(id, name, PacketSide.CLIENTBOUND, clazz, factory);
    }

    <P extends com.guichaguri.packetcontrol.api.Packet, A>
    void registerPacketType(String id, String name, PacketSide side, Class<? extends Packet> packetClass, PacketFactory<P, A> factory) {
        PacketControlType<P, A> type = new PacketControlType<>(id, name, side, packetClass, factory);

        packetMappings.put(id, type);
        packetTypeMappings.put(packetClass, type);
    }

    private static class Holder {
        static final PacketRegistryModule INSTANCE = new PacketRegistryModule();
    }

}
