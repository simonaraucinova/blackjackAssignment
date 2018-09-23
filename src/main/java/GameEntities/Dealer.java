package GameEntities;

import Authorities.CardManipulator;

/**
 * Represents Dealer (special case of player)
 *
 * Author: SimRau
 */
public class Dealer extends Player{

    public Dealer() {
        super("Dealer");
    }

    /**
     * Print cards of dealer.
     *
     * @param finalPrint should be the secret card shown
     */
    public void printCardsOfPlayer(boolean finalPrint) {
        if(getCardsOnHand().get(0).getValue() < 10 && !finalPrint){
            System.out.println("Dealer's cards: [" + getCardsOnHand().get(0).toString() + ", SECRET CARD]");
        } else {
            System.out.println("Dealer's cards: " + getCardsOnHand().toString());
        }
    }

    /**
     * Plays Dealer's turn.
     *
     * @param deck actual Deck
     */
    public void play(Deck deck){
        while (this.getActualCount() < 17){
            System.out.println("\nDealer must hit.");
            CardManipulator.hit(this,deck);
            printCardsOfPlayer(true);
        }
    }
}
