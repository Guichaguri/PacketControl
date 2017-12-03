package com.guichaguri.packetcontrol.api;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.packets.play.client.PacketMoveVehicle;
import com.guichaguri.packetcontrol.api.packets.play.client.PacketUpdateSign;
import com.guichaguri.packetcontrol.api.packets.play.entity.*;
import com.guichaguri.packetcontrol.api.packets.play.player.PacketPlayerAbilities;
import com.guichaguri.packetcontrol.api.packets.play.player.PacketSpawnPlayer;
import com.guichaguri.packetcontrol.api.packets.play.player.PacketUseBed;
import com.guichaguri.packetcontrol.api.packets.play.player.PacketVehiclePosition;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketCustomSound;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketGameSound;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketOpenSignEditor;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketWorldTime;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.ExperienceOrb;
import org.spongepowered.api.entity.hanging.Painting;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.util.generator.dummy.DummyObjectProvider;
import org.spongepowered.api.world.storage.WorldProperties;

/**
 * @author Guichaguri
 */
@SuppressWarnings("unchecked")
public final class PacketTypes {

    // SORTFIELDS:ON

    // Entity-based Packets
    public static PacketType<PacketDestroyEntities, Entity[]> DESTROY_ENTITIES = DummyObjectProvider.createFor(PacketType.class, "DESTROY_ENTITIES");
    public static PacketType<PacketEntityMetadata, Entity> ENTITY_METADATA = DummyObjectProvider.createFor(PacketType.class, "ENTITY_METADATA");
    public static PacketType<PacketEntityVelocity, Entity> ENTITY_VELOCITY = DummyObjectProvider.createFor(PacketType.class, "ENTITY_VELOCITY");
    public static PacketType<PacketSpawnExperienceOrb, ExperienceOrb> SPAWN_EXPERIENCE_ORB = DummyObjectProvider.createFor(PacketType.class, "SPAWN_EXPERIENCE_ORB");
    public static PacketType<PacketSpawnGlobalEntity, Entity> SPAWN_GLOBAL_ENTITY = DummyObjectProvider.createFor(PacketType.class, "SPAWN_GLOBAL_ENTITY");
    public static PacketType<PacketSpawnMob, Living> SPAWN_MOB = DummyObjectProvider.createFor(PacketType.class, "SPAWN_MOB");
    public static PacketType<PacketSpawnObject, Entity> SPAWN_OBJECT = DummyObjectProvider.createFor(PacketType.class, "SPAWN_OBJECT");
    public static PacketType<PacketSpawnPainting, Painting> SPAWN_PAINTING = DummyObjectProvider.createFor(PacketType.class, "SPAWN_PAINTING");

    // Player-based Packets
    public static PacketType<PacketPlayerAbilities, Player> PLAYER_ABILITIES = DummyObjectProvider.createFor(PacketType.class, "PLAYER_ABILITIES");
    public static PacketType<PacketSpawnPlayer, Player> SPAWN_PLAYER = DummyObjectProvider.createFor(PacketType.class, "SPAWN_PLAYER");
    public static PacketType<PacketUseBed, Player> USE_BED = DummyObjectProvider.createFor(PacketType.class, "USE_BED");
    public static PacketType<PacketVehiclePosition, Entity> VEHICLE_POSITION = DummyObjectProvider.createFor(PacketType.class, "VEHICLE_POSITION");

    // World-based Packets
    public static PacketType<PacketCustomSound, SoundType> CUSTOM_SOUND = DummyObjectProvider.createFor(PacketType.class, "CUSTOM_SOUND");
    public static PacketType<PacketGameSound, SoundType> GAME_SOUND = DummyObjectProvider.createFor(PacketType.class, "GAME_SOUND");
    public static PacketType<PacketOpenSignEditor, Vector3d> OPEN_SIGN_EDITOR = DummyObjectProvider.createFor(PacketType.class, "OPEN_SIGN_EDITOR");
    public static PacketType<PacketWorldTime, WorldProperties> WORLD_TIME = DummyObjectProvider.createFor(PacketType.class, "WORLD_TIME");

    // Serverbound Packets (packets that clients send to the server)
    public static PacketType<PacketMoveVehicle, Void> MOVE_VEHICLE = DummyObjectProvider.createFor(PacketType.class, "MOVE_VEHICLE");
    public static PacketType<PacketUpdateSign, Void> UPDATE_SIGN = DummyObjectProvider.createFor(PacketType.class, "UPDATE_SIGN");

    // SORTFIELDS:OFF

    private PacketTypes() {}
}
