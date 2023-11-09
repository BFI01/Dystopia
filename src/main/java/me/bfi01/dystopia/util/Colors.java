package me.bfi01.dystopia.util;

public enum Colors {
    DARK_PURPLE(0x892fbd),
    GREEN(0x089c20),
    LIGHT_PURPLE(0x7f49b8);

    private final int value;
    Colors(int s) {
        this.value = s;
    }

    public int getValue() {
        return this.value;
    }
}
