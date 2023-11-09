package me.bfi01.dystopia.managers;

import me.bfi01.dystopia.Dystopia;
import me.bfi01.dystopia.accounts.Account;
import me.bfi01.dystopia.listeners.StaminaListener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;

public class StaminaManager {
    private final Dystopia plugin;

    private static final int DEFAULT_STAMINA_REGEN = 20;

    public StaminaManager(Dystopia plugin) {
        this.plugin = plugin;
        new StaminaListener(plugin);
        staminaRegenerationTask();
    }

    private void staminaRegenerationTask() {
        new BukkitRunnable(){
            @Override
            public void run() {
                HashSet<Account> accounts = plugin.getAccountManager().getAccounts();
                for (Account account : accounts) {
                    if (account.canRegenStamina()) {
                        account.changeStamina((int) (DEFAULT_STAMINA_REGEN * account.getStaminaRegenModifier()));
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 10L);
    }
}
