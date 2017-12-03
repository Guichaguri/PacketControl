package com.guichaguri.packetcontrol.api.util;

import com.flowpowered.math.vector.Vector3d;

public interface Motionable {
    Vector3d getVelocity();

    void setVelocity(Vector3d velocity);
}
