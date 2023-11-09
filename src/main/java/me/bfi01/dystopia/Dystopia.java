package me.bfi01.dystopia;

import me.bfi01.dystopia.managers.AccountManager;
import me.bfi01.dystopia.managers.StaminaManager;
import me.bfi01.dystopia.managers.SidebarManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Dystopia extends JavaPlugin {
    public static final String PLUGIN_NAME = "Dystopia";

    private AccountManager accountManager;

    @Override
    public void onEnable() {
        // Initialise managers
        accountManager = new AccountManager(this);
        new StaminaManager(this);
        new SidebarManager(this);

        getLogger().info("Successfully loaded " + PLUGIN_NAME + " v" + this.getPluginMeta().getVersion());
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        getLogger().info("Successfully unloaded " + PLUGIN_NAME);
    }

    public AccountManager getAccountManager() {
        return accountManager;
    }
}
