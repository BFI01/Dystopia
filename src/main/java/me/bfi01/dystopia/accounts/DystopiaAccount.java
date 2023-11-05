package me.bfi01.dystopia.accounts;

import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class DystopiaAccount {
    private final Player player;
    private boolean showingTitle = false;
    private boolean playingSidebarAnimation = false;
    private final ArrayList<Integer> tasks;
    private boolean justJoinedLobby = false;
    private boolean resourcePack = false;

    private int stamina;

    public DystopiaAccount(Player player){
        this.player = player;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Integer taskID) {
        tasks.add(taskID);
    }

    public void removeTask(Integer taskID) {
        tasks.remove(taskID);
    }

    public ArrayList<Integer> getTasks() {
        return tasks;
    }

    public UUID getUUID() {
        return player.getUniqueId();
    }

    public String getName() {
        return player.getName();
    }

    public boolean isShowingTitle() {
        return showingTitle;
    }

    public void setShowingTitle(boolean showingTitle) {
        this.showingTitle = showingTitle;
    }

    public boolean isPlayingSidebarAnimation() {
        return playingSidebarAnimation;
    }

    public void setPlayingSidebarAnimation(boolean playingSidebarAnimation) {
        this.playingSidebarAnimation = playingSidebarAnimation;
    }

    public World getWorld() {
        return player.getWorld();
    }

    public boolean hasJustJoinedLobby() {
        return justJoinedLobby;
    }

    public void setJustJoinedLobby(boolean justJoinedLobby) {
        this.justJoinedLobby = justJoinedLobby;
    }

    public boolean hasResourcePack() {
        return resourcePack;
    }

    public void setResourcePack(boolean enabled) {
        resourcePack = enabled;
    }
}
