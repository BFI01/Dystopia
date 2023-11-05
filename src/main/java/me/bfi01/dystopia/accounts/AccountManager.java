package me.bfi01.dystopia.accounts;

import me.bfi01.dystopia.Dystopia;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class AccountManager {
    private final Dystopia plugin;
    private final HashSet<DystopiaAccount> accounts;

    public AccountManager(Dystopia plugin) {
        this.plugin = plugin;
        this.accounts = new HashSet<>();
        new AccountListener(plugin, this);
    }

    private DystopiaAccount createAccount(Player player) {
        DystopiaAccount account = new DystopiaAccount(player);
        accounts.add(account);
        return account;
    }

    private void removeAccount(DystopiaAccount account) {
        accounts.remove(account);
    }

    public DystopiaAccount removeAccount(Player player) {
        DystopiaAccount account = accounts.stream()
                .filter(a -> a.getUUID() == player.getUniqueId())
                .findFirst()
                .orElse(null);
        if (account == null) {
            plugin.getLogger().warning("Unable to remove account for " + player.displayName() + " as no account exists.");
            return null;
        }
        removeAccount(account);
        return account;
    }

    public DystopiaAccount getAccount(Player player) {
        return accounts.stream()
                .filter(account -> account.getUUID().equals(player.getUniqueId()))
                .findFirst()
                .orElseGet(() -> createAccount(player));
    }

    public HashSet<DystopiaAccount> getAccounts() {
        return accounts;
    }

    public List<DystopiaAccount> getAccounts(World world) {
        return accounts.stream()
                .filter(account -> account.getWorld().equals(world))
                .collect(Collectors.toList());
    }
}
