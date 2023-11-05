package me.bfi01.dystopia.util;

public enum Emoji {
    RIGHT_POINT_TRIANGLE("\u25B6"), // ▶
    RIGHT_ARROW("\u279C"), // ➜
    DOUBLE_RIGHT_ARROW("\u00BB"), // »
    STAR("\u2B50"), // https://emojipedia.org/star/
    CROSS("\u2716"); // ✖ https://emojipedia.org/multiply/

    private final String code;
    Emoji(String s) {
        this.code = s;
    }

    public String getCode() {
        return this.code;
    }
}
