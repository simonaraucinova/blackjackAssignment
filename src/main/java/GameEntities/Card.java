package GameEntities;

import CardProperties.Rank;
import CardProperties.Suit;

/**
 * Represents card.
 *
 * Author: SimRau
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
        StringBuilder sb = new StringBuilder();
        sb.append(getSuitChar(suit));
        sb.append(getRankString(rank));
        return sb.toString();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Helps to represent Suit on card.
     *
     * @param suit suit on card
     * @return char representing suit
     */
    private char getSuitChar(Suit suit){
        switch (suit){
            case CLUB:
                return ((char) '\u2663');
            case DIAMOND:
                return ((char)'\u2666');
            case HEART:
                return ((char)'\u2764');
            case SPADE:
                return ((char)'\u2660');
            default:
                return ((char)'\u0000');
        }
    }

    /**
     * Helps to represent Rank on card.
     *
     * @param rank rank on card
     * @return string representing rank
     */
    private String getRankString(Rank rank){
        switch (rank){
            case ACE:
                return "A";
            case KING:
                return "K";
            case QUEEN:
                return "Q";
            case JACK:
                return "J";
            case TEN:
                return "10";
            case NINE:
                return "9";
            case EIGHT:
                return "8";
            case SEVEN:
                return "7";
            case SIX:
                return "6";
            case FIVE:
                return "5";
            case FOUR:
                return "4";
            case THREE:
                return "3";
            case TWO:
                return "2";
            default:
                return "";
        }
    }
}
