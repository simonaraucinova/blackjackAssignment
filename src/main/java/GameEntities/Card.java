package GameEntities;

import CardProperties.Rank;
import CardProperties.Suit;

/**
 * Created by Simi on 21.9.2018.
 */

public class Card {

    private Suit suit;
    private Rank rank;
    private int value;

    public Card(Suit suit, Rank rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (suit != card.suit) return false;
        return rank == card.rank;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + rank.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Card: " + suit + " " + rank;
    }
}
