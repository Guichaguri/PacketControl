package com.guichaguri.packetcontrol.mcp.plugin.util;

import com.guichaguri.packetcontrol.api.PacketPhase;
import net.minecraft.network.EnumConnectionState;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.common.entity.SpongeEntityType;

/**
 * @author Guichaguri
 */
public class PacketUtils {

    public static byte compileRotationByte(double value) {
        return (byte)compileRotation(value);
    }

    public static int compileRotation(double value) {
        return (int)Math.floor(value * 256F / 360F);
    }

    public static double decompileRotation(int value) {
        return value * 360F / 256F;
    }

    public static int compileVelocity(double value) {
        return (int)(Math.min(3.9, Math.max(-3.9, value)) * 8000D);
    }

    public static double decompileVelocity(int value) {
        return value / 8000D;
    }

    public static int getTypeId(EntityType type) {
        if(type instanceof SpongeEntityType) {
            return ((SpongeEntityType)type).entityTypeId;
        } else {
            throw new RuntimeException("Invalid EntityType");
        }
    }

    public static EnumConnectionState toState(PacketPhase phase) {
        switch(phase) {
            case HANDSHAKE:
                return EnumConnectionState.HANDSHAKING;
            case STATUS:
                return EnumConnectionState.STATUS;
            case LOGIN:
                return EnumConnectionState.LOGIN;
            case PLAY:
                return EnumConnectionState.PLAY;
        }
        return EnumConnectionState.LOGIN;
    }

    public static PacketPhase fromState(EnumConnectionState phase) {
        switch(phase) {
            case HANDSHAKING:
                return PacketPhase.HANDSHAKE;
            case STATUS:
                return PacketPhase.STATUS;
            case LOGIN:
                return PacketPhase.LOGIN;
            case PLAY:
                return PacketPhase.PLAY;
        }
        return PacketPhase.LOGIN;
    }

}
