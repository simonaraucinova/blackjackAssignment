package GameEntities;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents player in game.
 *
 * Author: SimRau
 */
public class Player {

    private String name;
    private List<Card> cardsOnHand = new ArrayList<>();
    private int actualCount;
    private boolean standing;
    private boolean surrender;
    private short bet;
    private float account;

    public Player(String name) {
        this.name = name;
        this.cardsOnHand = new ArrayList<>();
        this.account = 0;
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

    public void setSurrender(boolean surrender) {
        this.surrender = surrender;
    }

    public float getAccount() {
        return account;
    }

    public void setAccount(float account) {
        this.account = account;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(short bet) {
        this.bet = bet;
    }

    /**
     * Check if a player has ace with value 11 and returns it.
     *
     * @return ace on hand with value 11
     */
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

    /**
     * Prints cards of a player.
     */
    public void printCardsOfPlayer(){
        System.out.println(this.getName() + "'s cards: " + this.getCardsOnHand().toString());
    }


}
