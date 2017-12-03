package com.guichaguri.packetcontrol.mixins.play.inventory;

import com.guichaguri.packetcontrol.api.packets.play.inventory.PacketOpenWindow;
import net.minecraft.network.play.server.SPacketOpenWindow;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.item.inventory.util.ContainerUtil;
import org.spongepowered.common.registry.type.item.InventoryArchetypeRegistryModule;
import org.spongepowered.common.text.SpongeTexts;

@Mixin(SPacketOpenWindow.class)
public abstract class MixinPacketOpenWindow implements PacketOpenWindow {

    @Shadow private int windowId;
    @Shadow private String inventoryType;
    @Shadow private ITextComponent windowTitle;
    @Shadow private int slotCount;
    @Shadow private int entityId;

    @Override
    public int getWindowId() {
        return windowId;
    }

    @Override
    public void setWindowId(int windowId) {
        this.windowId = windowId;
    }

    @Override
    public void setWindowId(Container container) {
        windowId = ContainerUtil.toNative(container).windowId;
    }

    @Override
    public InventoryArchetype getInventoryType() {
        return InventoryArchetypeRegistryModule.getInstance().getById(inventoryType).orElse(InventoryArchetypes.UNKNOWN);
    }

    @Override
    public void setInventoryType(InventoryArchetype inventoryType) {
        this.inventoryType = inventoryType.getId();
    }

    @Override
    public Text getTitle() {
        return SpongeTexts.toText(windowTitle);
    }

    @Override
    public void setTitle(Text title) {
        this.windowTitle = SpongeTexts.toComponent(title);
    }

    @Override
    public int getSlotCount() {
        return slotCount;
    }

    @Override
    public void setSlotCount(int slotCount) {
        this.slotCount = slotCount;
    }

    @Override
    public int getEntityId() {
        return entityId;
    }

    @Override
    public void setEntityId(int id) {
        this.entityId = id;
    }

    @Override
    public void setEntityId(Entity entity) {
        entityId = ((net.minecraft.entity.Entity)entity).getEntityId();
    }
}
