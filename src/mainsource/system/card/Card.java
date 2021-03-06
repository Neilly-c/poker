package mainsource.system.card;

public final class Card implements Comparable<Card> {

    private final CardValue value;
    private final Suit suit;

    public Card(CardValue value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public CardValue getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getNumber() {
        return this.value.getValue() + 13 * this.suit.getValue() - 14;
    }

    @Override
    public String toString() {
        return this.value.name() + " of " + this.suit.name() + ": " + this.getNumber();
    }

    public String toAbbreviateString(){
        return this.value.getAbb() + this.suit.getAbb();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Card card = (Card) o;

        return getValue() == card.getValue() && getSuit() == card.getSuit();
    }

    @Override
    public int hashCode() {
        int result = getValue().hashCode();
        result = 31 * result + getSuit().hashCode();
        return result;
    }

    @Override
    public int compareTo(Card o) {
        final boolean otherIsAnAce = CardValue.ACE.equals(o.getValue());
        final boolean iamAnAce = CardValue.ACE.equals(this.value);
        if (iamAnAce && !otherIsAnAce) {
            return 1;
        }
        if (!iamAnAce && otherIsAnAce) {
            return -1;
        }
        return this.value.compareTo(o.getValue());
    }
}
