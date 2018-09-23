package GameEntities;

import CardProperties.Rank;
import CardProperties.Suit;

import java.util.*;

/**
 * Represents deck in one round of game.
 *
 * Author: SimRau
 */
public class Deck {

    Stack<Card> content = new Stack<>();

    public Deck(int decksCount) {
        this.content.addAll(initializeDeck(decksCount));
    }

    /**
     * Fills the deck and shuffles it.
     *
     * @param decksCount how many 52-cards deck should be included in game
     * @return shuffled deck represented as List
     */
    public List<Card> initializeDeck(int decksCount){
        Map<String,Integer> cardValues = initializeRankValues();
        List<Card> cardList = new ArrayList<>();

        for(Suit suit : Suit.values()) {
            for(Rank rank : Rank.values()) {
                for(int i = 0; i < decksCount; i++) {
                    Card card = new Card(suit, rank, cardValues.get(rank.toString()));
                    cardList.add(card);
                }
            }
        }

        Collections.shuffle(cardList);
        return cardList;
    }

    /**
     * Pops card from top of deck.
     *
     * @return card from top of deck
     */
   public Card popCard(){
        if (this.content.isEmpty()){
            System.out.println("Oh no, deck is empty!");
            return null;
        } else {
            return content.pop();
        }
   }

    private Map<String,Integer> initializeRankValues(){
        Map<String,Integer> cardValues = new HashMap<>();
        cardValues.put("TWO",2);
        cardValues.put("THREE",3);
        cardValues.put("FOUR",4);
        cardValues.put("FIVE",5);
        cardValues.put("SIX",6);
        cardValues.put("SEVEN",7);
        cardValues.put("EIGHT",8);
        cardValues.put("NINE",9);
        cardValues.put("TEN",10);
        cardValues.put("JACK",10);
        cardValues.put("QUEEN",10);
        cardValues.put("KING",10);
        cardValues.put("ACE",11);

        return cardValues;
    }

}
