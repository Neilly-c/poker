package mainsource.system.card;

public enum CardValue {

    ACE(1, "A"),
    TWO(2, "2"),
    THREE(3, "3"),
    FOUR(4, "4"),
    FIVE(5, "5"),
    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "T"),
    JACK(11, "J"),
    QUEEN(12, "Q"),
    KING(13, "K");

    private final int value;
    private final String abb;

    CardValue(int value, String abb) {
        this.value = value;
        this.abb = abb;
    }

    public int getValue() {
        return value;
    }

    public String getAbb() {
        return abb;
    }

    public static CardValue getCardValueFromInt(int i){
        for(CardValue c : CardValue.values()){
            if(c.getValue() == i)
                return c;
        }
        return ACE;
    }
}
