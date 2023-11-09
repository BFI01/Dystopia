package me.bfi01.dystopia.listeners;

import me.bfi01.dystopia.Dystopia;
import me.bfi01.dystopia.accounts.Account;
import me.bfi01.dystopia.managers.AccountManager;
import me.bfi01.dystopia.enums.StaminaDefaultCosts;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBucketFillEvent;


public class StaminaListener implements DefaultListener {
    private final Dystopia plugin;
    private final AccountManager accountManager;

    public StaminaListener(Dystopia plugin) {
        this.plugin = plugin;
        this.accountManager = plugin.getAccountManager();
        // Register listener
        register(plugin);
    }

    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        Account account = accountManager.getAccount(player);

        int staminaCost = StaminaDefaultCosts.BUCKET_FILL.getDefaultCost();
        if (account.getStamina() < staminaCost) {
            event.setCancelled(true);
            Component actionMessage = Component.text("Out of stamina!");
            player.sendActionBar(actionMessage);
        } else {
            account.changeStamina(-staminaCost);
        }
    }
}
