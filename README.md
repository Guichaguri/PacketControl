# PacketControl
A packet/protocol library for SpongeForge and SpongeVanilla

## Features
* **Modify packets!** Every packet is full of getters and setters for every property. Most of them also are built on top of the Sponge API, making it easier to modify.
* **Create packets!** Every packet has a default "constructor" and a custom one which allows you to create a packet based on an existing object.
* **Send packets!** You can not only send the packet to a specific player, but you can also send it based on distance or even on a entity tracker.
* **Intercept packets!** Every packet can be intercepted, modified and cancelled (both serverbound and clientbound packets)
* **Interfaces everywhere!** You don't need to write MCP-dependent code, we do all of that for you.

## Getting Started
First of all, you'll need to access the service:
```java
Optional<PacketService> optional = Sponge.getServiceManager().provide(PacketService.class);
```

You can create and send a packet
```java
// Create a packet passing a player to its constructor
PacketUseBed packet = service.createPacket(PacketTypes.USE_BED, player);

// Send the packet to all entities near the player
service.sendPacketToTracking(packet, player);
```

You can also intercept packets
```java
// Registers a packet handler using a lambda function
// You can also use method references or even whole classes
service.registerHandler(PacketTypes.WORLD_TIME, (con, packet) -> {
    
    // Change the world time to a random value
    packet.setWorldTime((long)(Math.random() * 24000));
    
    // Whether the packet should not be sent
    return false;
    
});
```

## Why?
#### API Limitations
This is the main reason why protocol libraries became popular on Bukkit.

Unlike Bukkit, Sponge aims to have every vanilla feature possible on its API, but until those are suggested and implemented, you can use this plugin to do so.
Also, some features might not fit into the API and will never be implemented.

#### Bugs
There are a lot of bugs that you can explore with packets, and because of that, they will never be added to the Sponge API.
Some of them are pretty interesting, such as the animated MOTDs bug.

#### Undetectable Modifications
To disguise a player on Sponge, you can hide the player and spawn an entity on top, but hacked clients and mods can detect that based on the packet discrepancy.
That can be a problem if you're trying to use it as a administration tool.