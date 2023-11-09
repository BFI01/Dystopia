package me.bfi01.dystopia.managers;

import me.bfi01.dystopia.Dystopia;
import me.bfi01.dystopia.accounts.Account;
import me.bfi01.dystopia.listeners.AccountListener;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AccountManager {
    private final Dystopia plugin;
    private final HashSet<Account> accounts;

    public AccountManager(Dystopia plugin) {
        this.plugin = plugin;
        this.accounts = new HashSet<>();
        new AccountListener(plugin, this);
    }

    private Account createAccount(Player player) {
        Account account = new Account(plugin, player);
        accounts.add(account);
        return account;
    }

    private void removeAccount(Account account) {
        account.updatePersistentDataContainer();
        accounts.remove(account);
    }

    public void removeAccount(Player player) {
        Account account = accounts.stream()
                .filter(a -> a.getUUID() == player.getUniqueId())
                .findFirst()
                .orElse(null);
        if (account == null) {
            plugin.getLogger().warning("Unable to remove account for " + player.displayName() + " as no account exists.");
            return;
        }
        removeAccount(account);
    }

    public Account getAccount(Player player) {
        return accounts.stream()
                .filter(account -> account.getUUID().equals(player.getUniqueId()))
                .findFirst()
                .orElseGet(() -> createAccount(player));
    }

    public HashSet<Account> getAccounts() {
        return accounts;
    }

    public List<Account> getAccounts(World world) {
        return accounts.stream()
                .filter(account -> account.getWorld().equals(world))
                .collect(Collectors.toList());
    }
}
