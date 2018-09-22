package GameEntities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simi on 21.9.2018.
 */
public class Player {

    private String name;
    private List<Card> cardsOnHand = new ArrayList<>();
    private int actualCount;
    private boolean standing;
    private boolean surrender;
    private short bet;

    public Player(String name) {
        this.name = name;
        this.cardsOnHand = new ArrayList<>();
    }

    public List<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public int getActualCount() {
        return actualCount;
    }

    public void setActualCount(int actualCount) {
        this.actualCount = actualCount;
    }

    public String getName() {
        return name;
    }

    public boolean isStanding() {
        return standing;
    }

    public void setStanding(boolean standing) {
        this.standing = standing;
    }

    public boolean isSurrender() {
        return surrender;
    }

    public void surrender() {
        this.surrender = true;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(short bet) {
        this.bet = bet;
    }

    public Card getAceOnHand(){
        for(Card card: this.getCardsOnHand()){
            if (card.getValue() == 11){
                return card;
            }
        }

        return null;
    }

    public boolean hasBlackJack(){
        return ((getActualCount()>= 21) && getCardsOnHand().size() == 2);
    }

    public void printCardsOfPlayer(){
        System.out.println(this.getName() + " has cards: " + this.getCardsOnHand().toString());
    }


}
