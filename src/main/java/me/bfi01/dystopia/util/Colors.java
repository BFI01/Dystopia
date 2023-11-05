package me.bfi01.dystopia.util;

public enum Colors {
    RED(0x283a7);

    private final int value;
    Colors(int s) {
        this.value = s;
    }

    public int getValue() {
        return this.value;
    }
}
