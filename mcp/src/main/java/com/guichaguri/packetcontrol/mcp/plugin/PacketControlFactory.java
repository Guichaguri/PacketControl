package com.guichaguri.packetcontrol.mcp.plugin;

import com.flowpowered.math.vector.Vector3d;
import com.guichaguri.packetcontrol.api.packets.play.client.PacketClickWindow;
import com.guichaguri.packetcontrol.api.packets.play.entity.PacketDestroyEntities;
import com.guichaguri.packetcontrol.api.packets.play.entity.PacketEntityMetadata;
import com.guichaguri.packetcontrol.api.packets.play.entity.PacketSpawnObject;
import com.guichaguri.packetcontrol.api.packets.play.inventory.PacketCloseWindow;
import com.guichaguri.packetcontrol.api.packets.play.inventory.PacketConfirmTransaction;
import com.guichaguri.packetcontrol.api.packets.play.inventory.PacketOpenWindow;
import com.guichaguri.packetcontrol.api.packets.play.inventory.PacketSetSlot;
import com.guichaguri.packetcontrol.api.packets.play.player.PacketPlayerAbilities;
import com.guichaguri.packetcontrol.api.packets.play.player.PacketSetExperience;
import com.guichaguri.packetcontrol.api.packets.play.player.PacketUseBed;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketCustomSound;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketGameSound;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketOpenSignEditor;
import com.guichaguri.packetcontrol.api.packets.play.world.PacketWorldTime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.server.*;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.world.storage.WorldProperties;
import org.spongepowered.common.item.inventory.util.ContainerUtil;
import org.spongepowered.common.item.inventory.util.ItemStackUtil;
import org.spongepowered.common.text.SpongeTexts;
import org.spongepowered.common.util.VecHelper;

import java.util.Optional;

/**
 * @author Guichaguri
 */
public class PacketControlFactory {

    public static PacketDestroyEntities createDestroyEntities(Entity[] entities) {
        int[] ids = new int[entities.length];

        for(int i = 0; i < entities.length; i++) {
            ids[i] = ((net.minecraft.entity.Entity)entities[i]).getEntityId();
        }

        return (PacketDestroyEntities)new SPacketDestroyEntities(ids);
    }

    public static PacketEntityMetadata createEntityMetadata(Entity entity) {
        net.minecraft.entity.Entity e = (net.minecraft.entity.Entity)entity;

        return (PacketEntityMetadata)new SPacketEntityMetadata(e.getEntityId(), e.getDataManager(), true);
    }

    public static PacketSpawnObject createSpawnObject(Entity entity) {
        return (PacketSpawnObject)new SPacketSpawnObject();//TODO
    }

    public static PacketCloseWindow createCloseWindow(Container container) {
        net.minecraft.inventory.Container nativeContainer = ContainerUtil.toNative(container);

        return (PacketCloseWindow)new SPacketCloseWindow(nativeContainer.windowId);
    }

    public static PacketConfirmTransaction createConfirmTransaction(PacketClickWindow clickWindow) {
        return (PacketConfirmTransaction)new SPacketConfirmTransaction(
                clickWindow.getWindowId(),
                clickWindow.getTransactionId(),
                true);
    }

    public static PacketOpenWindow createOpenWindow(Container container) {
        net.minecraft.inventory.Container nativeContainer = ContainerUtil.toNative(container);
        ITextComponent title = SpongeTexts.toComponent(container.getArchetype().getProperty(InventoryTitle.class).get().getValue());

        return (PacketOpenWindow)new SPacketOpenWindow(
                nativeContainer.windowId, container.getArchetype().getId(),
                title, nativeContainer.inventorySlots.size());
    }

    public static PacketSetSlot createSetSlot(Slot slot) {
        net.minecraft.inventory.Slot nativeSlot = ((net.minecraft.inventory.Slot)(Object)slot);

        return (PacketSetSlot)new SPacketSetSlot(0, nativeSlot.slotNumber, nativeSlot.getStack());
    }

    public static PacketSetSlot createWindowItems(Container container) {
        net.minecraft.inventory.Container nativeContainer = ContainerUtil.toNative(container);

        return (PacketSetSlot)new SPacketWindowItems(
                nativeContainer.windowId,
                nativeContainer.inventoryItemStacks);
    }

    public static PacketPlayerAbilities createPlayerAbilities(Player player) {
        return (PacketPlayerAbilities)new SPacketPlayerAbilities(((EntityPlayer)player).capabilities);
    }

    public static PacketUseBed createUseBed(Player player) {
        EntityPlayer p = (EntityPlayer)player;

        return (PacketUseBed)new SPacketUseBed(p, p.getPosition());
    }

    public static PacketCustomSound createCustomSound(SoundType type) {
        return (PacketCustomSound)new SPacketCustomSound(type.getId(), SoundCategory.MASTER, 0, 0, 0, 1, 1);
    }

    public static PacketGameSound createGameSound(SoundType type) {
        return (PacketGameSound)new SPacketSoundEffect((SoundEvent)type, SoundCategory.MASTER, 0, 0, 0, 1, 1);
    }

    public static PacketOpenSignEditor createOpenSignEditor(Vector3d pos) { // TODO adapt automatically?
        return (PacketOpenSignEditor)new SPacketSignEditorOpen(VecHelper.toBlockPos(pos));
    }

    public static PacketWorldTime createWorldTime(WorldProperties properties) {
        boolean cycle = Boolean.parseBoolean(properties.getGameRule("doDaylightCycle").orElse("true"));

        return (PacketWorldTime)new SPacketTimeUpdate(properties.getTotalTime(), properties.getWorldTime(), cycle);
    }
}
