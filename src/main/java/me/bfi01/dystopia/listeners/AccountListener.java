package me.bfi01.dystopia.listeners;

import me.bfi01.dystopia.Dystopia;
import me.bfi01.dystopia.accounts.Account;
import me.bfi01.dystopia.managers.AccountManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

/**
 * {@link Listener} to handle {@link Account}s on the server.
 */
public class AccountListener implements DefaultListener {
    private final Dystopia plugin;
    private final AccountManager accountManager;

    /**
     * Constructs and registers the listener.
     *
     * @param plugin {@link Plugin} object representing the plugin.
     * @param accountManager {@link AccountManager} object.
     */
    public AccountListener(Dystopia plugin, AccountManager accountManager) {
        this.plugin = plugin;
        this.accountManager = accountManager;

        // Register listener
        register(plugin);
    }

    /**
     * {@link Listener} for when a player joins the server.
     *
     * @param event {@link PlayerJoinEvent} object for this event.
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        // Get the player's account (or create it if it does not exist)
        accountManager.getAccount(player);

        plugin.getLogger().info("Account connected: " + player.getName() + " (" + player.getUniqueId() + ")");
        plugin.getLogger().info("Accounts connected: " + accountManager.getAccounts().size());
    }

    /**
     * {@link Listener} for when a player leaves the server.
     *
     * @param event {@link PlayerQuitEvent} object for this event.
     */
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        // Remove the account from the AccountManager
        accountManager.removeAccount(player);
        plugin.getLogger().info("Account disconnected: " + player.getName() + " (" + player.getUniqueId() + ")");
        plugin.getLogger().info("Accounts connected: " + accountManager.getAccounts().size());
    }
}
