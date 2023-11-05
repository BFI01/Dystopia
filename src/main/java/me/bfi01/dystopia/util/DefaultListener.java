package me.bfi01.dystopia.util;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public interface DefaultListener extends Listener {
    default boolean register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        return true;
    }

    default boolean unregister() {
        HandlerList.unregisterAll(this);
        return false;
    }
}
