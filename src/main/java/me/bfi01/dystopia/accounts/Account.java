package me.bfi01.dystopia.accounts;

import me.bfi01.dystopia.Dystopia;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Account {
    private final Dystopia plugin;
    private static final int MAX_STAMINA = 1000;
    private static final int MAX_THIRST = 100;

    private final Player player;
    private final ArrayList<Integer> tasks;
    private boolean showingTitle = false;
    private boolean playingSidebarAnimation = false;
    private boolean justJoinedLobby = false;
    private boolean resourcePack = false;

    private final HashMap<String, NamespacedKey> namespacedKeys = new HashMap<>();
    private int stamina;
    private boolean canRegenStamina = true;
    private double staminaRegenModifier = 1.0;

    private int thirst;

    public Account(Dystopia plugin, Player player){
        this.plugin = plugin;
        this.player = player;
        this.tasks = new ArrayList<>();
//        new XSidebar(this, player);

        stamina = retrievePersistentIntegerData("stamina", MAX_STAMINA);
        thirst = retrievePersistentIntegerData("thirst", MAX_THIRST);
    }

    private int retrievePersistentIntegerData(String keyName, int defaultValue) {
        // Define NamespacedKey and get player PDC
        NamespacedKey key;
        if (namespacedKeys.containsKey(keyName)) {
            key = namespacedKeys.get(keyName);
        } else {
            key = new NamespacedKey(plugin, keyName);
            namespacedKeys.put(keyName, key);
        }
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        // Set to default value if the player does not have an existing value for the key
        // Otherwise, return key value
        if (playerContainer.has(key, PersistentDataType.INTEGER)) {
            Integer value = playerContainer.get(key, PersistentDataType.INTEGER);
            return value != null ? value : defaultValue;
        }
        playerContainer.set(key, PersistentDataType.INTEGER, defaultValue);
        return defaultValue;
    }

    public void updatePersistentDataContainer() {
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();

        for (Map.Entry<String, NamespacedKey> entry : namespacedKeys.entrySet()) {
            String keyName = entry.getKey();
            NamespacedKey key = entry.getValue();

            switch (keyName) {
                case "stamina" -> playerContainer.set(key, PersistentDataType.INTEGER, stamina);
                case "thirst" -> playerContainer.set(key, PersistentDataType.INTEGER, thirst);
            }
        }
    }

    public int getThirst() {
        return thirst;
    }

    public void changeThirst(int amount) {
        setThirst(Math.min(Math.max(thirst + amount, 0), MAX_THIRST));
    }

    public void setThirst(int value) {
        thirst = value;

        NamespacedKey thirstKey;
        if (namespacedKeys.containsKey("thirst")) {
            thirstKey = namespacedKeys.get("thirst");
        } else {
            thirstKey = new NamespacedKey(plugin, "thirst");
            namespacedKeys.put("thirst", thirstKey);
        }
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        playerContainer.set(thirstKey, PersistentDataType.INTEGER, thirst);
    }

    public int getStamina() {
        return Math.floorDiv(stamina, 10);
    }

    public void changeStamina(int amount) {
        // Ensures that (stamina + amount) is between 0 and MAX_STAMINA only
        setStamina(Math.min(Math.max(stamina + amount, 0), MAX_STAMINA));
    }

    public void setStamina(int value) {
        stamina = value;

        NamespacedKey staminaKey;
        if (namespacedKeys.containsKey("stamina")) {
            staminaKey = namespacedKeys.get("stamina");
        } else {
            staminaKey = new NamespacedKey(plugin, "stamina");
            namespacedKeys.put("stamina", staminaKey);
        }
        PersistentDataContainer playerContainer = player.getPersistentDataContainer();
        playerContainer.set(staminaKey, PersistentDataType.INTEGER, stamina);
    }

    public boolean canRegenStamina() {
        return canRegenStamina;
    }

    public void setCanRegenStamina(boolean value) {
        canRegenStamina = value;
    }

    public double getStaminaRegenModifier() {
        return staminaRegenModifier;
    }

    public void setStaminaRegenModifier(double value) {
        staminaRegenModifier = value;
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
