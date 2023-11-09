package me.bfi01.dystopia.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public interface DefaultListener extends Listener {
    default void register(JavaPlugin plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    default void unregister() {
        HandlerList.unregisterAll(this);
    }
}
