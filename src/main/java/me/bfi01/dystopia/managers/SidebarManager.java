package me.bfi01.dystopia.managers;

import dev.fumaz.bukkit.scoreboard.adventure.FastBoard;
import me.bfi01.dystopia.Dystopia;
import me.bfi01.dystopia.accounts.Account;
import me.bfi01.dystopia.listeners.SidebarListener;
import me.bfi01.dystopia.ui.sidebar.SidebarTitleAnimations;
import me.bfi01.dystopia.util.Colors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class SidebarManager {
    private final Dystopia plugin;
    private final Map<UUID, FastBoard> boards = new HashMap<>();

    private static final Component defaultTitle = Component.text("Dys", TextColor.color(Colors.GREEN.getValue()))
            .append(Component.text("topia", TextColor.color(Colors.DARK_PURPLE.getValue())));

    public SidebarManager(Dystopia plugin) {
        this.plugin = plugin;

        new SidebarListener(plugin, this);

        runUpdateTask();
    }

    private void runUpdateTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (FastBoard board : boards.values()) {
                    updateBoard(board);
//                    startAnimation(board);
                }
            }
        }.runTaskTimer(plugin, 0L, 10L);

    }

    public void startAnimation(FastBoard board) {
        Account account = plugin.getAccountManager().getAccount(board.getPlayer());

        BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, new Runnable(){
            private int lastAnimation = 5;

            @Override
            public void run() {
                if (!account.isPlayingSidebarAnimation()) {
                    int animation;
                    do {
                        animation = ThreadLocalRandom.current().nextInt(1, SidebarTitleAnimations.getTotalAnimationTypes() + 1);
                    } while (animation == lastAnimation);
                    lastAnimation = animation;
                    int delay = ThreadLocalRandom.current().nextInt(6, 13) * 10; // ticks

                    switch (animation) {
                        case 1 -> SidebarTitleAnimations.rightWave(plugin, board, account, delay);
                        case 2 -> SidebarTitleAnimations.leftWave(plugin, board, account, delay);
                        case 3 -> SidebarTitleAnimations.inwardsWave(plugin, board, account, delay);
                        case 4 -> SidebarTitleAnimations.blinking(plugin, board, account, delay);
                        case 5 -> SidebarTitleAnimations.raindrops(plugin, board, account, delay);
                        case 6 -> SidebarTitleAnimations.fillUp(plugin, board, account, delay);
                        case 7 -> SidebarTitleAnimations.checkerboard(plugin, board, account, delay);
                    }
                }
            }
        }, 100, 1);

        account.addTask(task.getTaskId());
    }

    public void updateBoard(FastBoard board) {
        Account account = plugin.getAccountManager().getAccount(board.getPlayer());
        board.updateLines(
                Component.text(DateTimeFormatter.ofPattern("dd MMM yyyy").format(LocalDate.now()), NamedTextColor.GRAY),
                Component.empty(),
                Component.text("Stamina:"),
                Component.text(account.getStamina())
        );
    }

    public Map<UUID, FastBoard> getBoards() {
        return boards;
    }

    public void removePlayer(Player player) {
        FastBoard board = boards.remove(player.getUniqueId());

        if (board != null) {
            board.delete();
        }
    }

    public static Component getDefaultTitle() {
        return defaultTitle;
    }
}
