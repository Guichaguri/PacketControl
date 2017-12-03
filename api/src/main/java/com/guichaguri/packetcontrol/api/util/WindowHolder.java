package com.guichaguri.packetcontrol.api.util;

import org.spongepowered.api.item.inventory.Container;

public interface WindowHolder {

    int getWindowId();

    void setWindowId(int windowId);

    void setWindowId(Container container);
}
