package me.bfi01.dystopia.listeners;

import dev.fumaz.bukkit.scoreboard.adventure.FastBoard;
import me.bfi01.dystopia.Dystopia;
import me.bfi01.dystopia.managers.SidebarManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class SidebarListener implements DefaultListener {
    private final Dystopia plugin;
    private final SidebarManager sidebarManager;

    public SidebarListener(Dystopia plugin, SidebarManager sidebarManager) {
        this.plugin = plugin;
        this.sidebarManager = sidebarManager;
        register(plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FastBoard board = new FastBoard(player);

        board.updateTitle(SidebarManager.getDefaultTitle());

        sidebarManager.getBoards().put(player.getUniqueId(), board);
        sidebarManager.updateBoard(board);
        sidebarManager.startAnimation(board);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        sidebarManager.removePlayer(player);
    }
}
