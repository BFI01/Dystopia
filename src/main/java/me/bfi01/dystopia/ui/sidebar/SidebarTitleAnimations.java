package me.bfi01.dystopia.ui.sidebar;

import dev.fumaz.bukkit.scoreboard.adventure.FastBoard;
import me.bfi01.dystopia.Dystopia;
import me.bfi01.dystopia.accounts.Account;
import me.bfi01.dystopia.managers.SidebarManager;
import me.bfi01.dystopia.util.Colors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.ThreadLocalRandom;

public class SidebarTitleAnimations {
    private static final int totalAnimationTypes = 7;

    private static final String text = "Dystopia";
    private static final TextColor primaryColor = TextColor.color(Colors.DARK_PURPLE.getValue());
    private static final TextColor secondaryColor = TextColor.color(Colors.GREEN.getValue());
    private static final TextColor tertiaryColor = TextColor.color(Colors.LIGHT_PURPLE.getValue());

    public static void rightWave(Dystopia plugin, FastBoard sidebar, Account account, int delay){
        account.setPlayingSidebarAnimation(true);

        BukkitTask task = new BukkitRunnable(){
            private int i = -1;
            private Component title = SidebarManager.getDefaultTitle();

            @Override
            public void run() {
                if (i >= text.length()) {
                    // End runnable once animation is finished
                    sidebar.updateTitle(SidebarManager.getDefaultTitle());
                    account.removeTask(this.getTaskId());
                    account.setPlayingSidebarAnimation(false);
                    cancel();
                } else {
                    if (i >= 0) {
                        title = Component.text(text.substring(0, i), primaryColor)
                                .append(Component.text(text.charAt(i), secondaryColor))
                                .append(Component.text(text.substring(i + 1), primaryColor));
                    }
                    sidebar.updateTitle(title);
                    i++;
                }
            }
        }.runTaskTimer(plugin, delay,2);

        account.addTask(task.getTaskId());
    }

    public static void leftWave(Dystopia plugin, FastBoard sidebar, Account account, int delay){
        account.setPlayingSidebarAnimation(true);

        BukkitTask task = new BukkitRunnable(){
            private int i = -1;
            private Component title = SidebarManager.getDefaultTitle();

            @Override
            public void run() {
                if (i >= text.length()) {
                    // End runnable once animation is finished
                    sidebar.updateTitle(SidebarManager.getDefaultTitle());
                    account.removeTask(this.getTaskId());
                    account.setPlayingSidebarAnimation(false);
                    cancel();
                } else {
                    if (i >= 0) {
                        int len = text.length()-1;
                        title = Component.text(text.substring(0, len - i), primaryColor)
                                .append(Component.text(text.charAt(len - i), secondaryColor))
                                .append(Component.text(text.substring(len - i + 1), primaryColor));
                    }
                    sidebar.updateTitle(title);
                    i++;
                }
            }
        }.runTaskTimer(plugin, delay,2);

        account.addTask(task.getTaskId());
    }

    public static void inwardsWave(Dystopia plugin, FastBoard sidebar, Account account, int delay){
        account.setPlayingSidebarAnimation(true);

        BukkitTask task = new BukkitRunnable(){
            private int i = 0;
            private int repeats = 0;
            private Component title = SidebarManager.getDefaultTitle();

            @Override
            public void run() {
                if (repeats > 1) {
                    // End runnable once animation is finished
                    account.setPlayingSidebarAnimation(false);
                    sidebar.updateTitle(SidebarManager.getDefaultTitle());
                    account.removeTask(this.getTaskId());
                    cancel();
                } else {
                    int end = text.length()-i;
                    if (i > end) {
                        if (repeats % 2 == 0) {
                            title = Component.text(text, secondaryColor);
                        } else {
                            title = Component.text(text, primaryColor);
                        }
                        i = 0;
                        repeats++;
                    } else if (i > 0) {
                        // Each repeat, swap between coloring for the wave effect
                        if (repeats % 2 == 0) {
                            title = Component.text(text.substring(0, i), secondaryColor)
                                    .append(Component.text(text.substring(i, end), primaryColor))
                                    .append(Component.text(text.substring(end), secondaryColor));
                        } else {
                            title = Component.text(text.substring(0, i), primaryColor)
                                    .append(Component.text(text.substring(i, end), secondaryColor))
                                    .append(Component.text(text.substring(end), primaryColor));
                        }
                    }
                    sidebar.updateTitle(title);
                    i++;
                }
            }
        }.runTaskTimer(plugin, delay,2);

        account.addTask(task.getTaskId());
    }

    public static void blinking(Dystopia plugin, FastBoard sidebar, Account account, int delay){
        account.setPlayingSidebarAnimation(true);

        BukkitTask task = new BukkitRunnable(){
            private int i = 0;

            @Override
            public void run() {
                if (i > 30) {
                    // End runnable once animation is finished
                    sidebar.updateTitle(SidebarManager.getDefaultTitle());
                    account.removeTask(this.getTaskId());
                    account.setPlayingSidebarAnimation(false);
                    cancel();
                } else {
                    if (Math.floor(i / 5.0) % 2 == 0) {
                        sidebar.updateTitle(Component.text(text, primaryColor));
                    } else {
                        sidebar.updateTitle(Component.text(text, secondaryColor));
                    }
                    i++;
                }
            }
        }.runTaskTimer(plugin, delay,2);

        account.addTask(task.getTaskId());
    }

    public static void raindrops(Dystopia plugin, FastBoard sidebar, Account account, int delay){
        account.setPlayingSidebarAnimation(true);

        BukkitTask task = new BukkitRunnable(){
            private int i = 0;
            private int repeats = 0;
            private Component title = SidebarManager.getDefaultTitle();
            private int index = 0;
            private boolean firstPass = true;

            @Override
            public void run() {
                if (repeats >= 6) {
                    // End runnable once animation is finished
                    sidebar.updateTitle(SidebarManager.getDefaultTitle());
                    account.removeTask(this.getTaskId());
                    account.setPlayingSidebarAnimation(false);
                    cancel();
                } else {
                    if (i <= 2) {
                        if (firstPass) {
                            index = ThreadLocalRandom.current().nextInt(0, text.length());
                            firstPass = false;
                        }
                        title = Component.text(text.substring(0, index), primaryColor)
                                .append(Component.text(text.charAt(index), tertiaryColor))
                                .append(Component.text(text.substring(index + 1), primaryColor));
                    } else if (i <= 5) {
                        title = Component.text(text.substring(0, index), primaryColor)
                                .append(Component.text(text.charAt(index), secondaryColor))
                                .append(Component.text(text.substring(index + 1), primaryColor));
                    } else if (i < 8) {
                        title = SidebarManager.getDefaultTitle();
                    } else {
                        i = 0;
                        repeats++;
                        firstPass = true;
                    }
                    sidebar.updateTitle(title);
                }
                i++;

            }
        }.runTaskTimer(plugin, delay,2);

        account.addTask(task.getTaskId());
    }

    public static void fillUp(Dystopia plugin, FastBoard sidebar, Account account, int delay){
        account.setPlayingSidebarAnimation(true);

        BukkitTask task = new BukkitRunnable(){
            private int i = 0;
            private Component title = SidebarManager.getDefaultTitle();
            private boolean reverse = false;

            @Override
            public void run() {
                if (i == 0 && reverse) {
                    // End runnable once animation is finished
                    sidebar.updateTitle(SidebarManager.getDefaultTitle());
                    account.removeTask(this.getTaskId());
                    account.setPlayingSidebarAnimation(false);
                    cancel();
                } else {
                    if (i >= text.length() && !reverse) {
                        reverse = true;
                        i += 10;
                    } else if (i < text.length()) {
                        title = Component.text(text.substring(0, i + 1), secondaryColor)
                                .append(Component.text(text.substring(i + 1), primaryColor));
                    }

                    i += reverse ? -1 : 1;
                    sidebar.updateTitle(title);

                }
            }
        }.runTaskTimer(plugin, delay,2);

        account.addTask(task.getTaskId());
    }

    public static void checkerboard(Dystopia plugin, FastBoard sidebar, Account account, int delay){
        account.setPlayingSidebarAnimation(true);

        BukkitTask task = new BukkitRunnable(){
            private int i = 0;

            @Override
            public void run() {
                if (i >= 40) {
                    // End runnable once animation is finished
                    sidebar.updateTitle(SidebarManager.getDefaultTitle());
                    account.removeTask(this.getTaskId());
                    account.setPlayingSidebarAnimation(false);
                    cancel();
                } else {
                    TextColor secondary;
                    TextColor primary;
                    if (Math.floor(i / 4.0) % 2 == 0) {
                        primary = primaryColor;
                        secondary = secondaryColor;

                    } else {
                        primary = secondaryColor;
                        secondary = primaryColor;
                    }
                    Component title = Component.empty();
                    for (int i = 0; i < text.length(); i++) {
                        if (i % 2 == 0) {
                            title = title.append(Component.text(text.charAt(i), primary));
                        } else {
                            title = title.append(Component.text(text.charAt(i), secondary));
                        }
                    }
                    sidebar.updateTitle(title);
                    i++;
                }
            }
        }.runTaskTimer(plugin, delay,2);

        account.addTask(task.getTaskId());
    }

    public static int getTotalAnimationTypes() {
        return totalAnimationTypes;
    }
}
