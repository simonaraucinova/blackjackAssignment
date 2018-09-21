package GameEntities;

import CardProperties.Rank;
import CardProperties.Suit;

import java.util.*;

/**
 * Created by Simi on 21.9.2018.
 */
public class Deck {

    Stack<Card> content = new Stack<>();

    private Map<String,Integer> initializeValues(){
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

    public void initializeDeck(int decksCount){

        Map<String,Integer> cardValues = initializeValues();
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
        this.content.addAll(cardList);
    }

   public Card popCard(){
        if (this.content.isEmpty()){
            System.out.println("Oh no, deck is empty!");
            return null;
        } else {
            return content.pop();
        }
   }

    public Stack<Card> getContent() {
        return content;
    }
}
