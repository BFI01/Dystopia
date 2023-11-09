package me.bfi01.dystopia.enums;

public enum StaminaDefaultCosts {
    BUCKET_FILL(50);

    private final int defaultCost;
    StaminaDefaultCosts(int cost) {
        this.defaultCost = cost;
    }

    public int getDefaultCost() {
        return this.defaultCost;
    }
}
