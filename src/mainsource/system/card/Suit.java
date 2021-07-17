package mainsource.system.card;

public enum Suit {

    CLUBS(1, "c"),
    DIAMONDS(2, "d"),
    HEARTS(3, "h"),
    SPADES(4, "s");

    private final int value;
    private final String abb;

    Suit(int value, String abb) {
        this.value = value;
        this.abb = abb;
    }

    public int getValue() {
        return value;
    }

    public String getAbb() {
        return abb;
    }
}
