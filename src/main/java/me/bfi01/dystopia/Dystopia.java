package me.bfi01.dystopia;

import me.bfi01.dystopia.players.StaminaListener;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dystopia extends JavaPlugin implements Listener {
    public static final String PLUGIN_NAME = "Dystopia";

    @Override
    public void onEnable() {
        getLogger().info("Successfully loaded " + PLUGIN_NAME + " v" + this.getPluginMeta().getVersion());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
