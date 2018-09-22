package GameEntities;


import Authorities.CardManipulator;

/**
 * Created by Simi on 22.9.2018.
 */
public class Dealer extends Player{

    public Dealer() {
        super("Dealer");
    }

    public void printCardsOfPlayer(boolean finalPrint) {
        if(getCardsOnHand().get(0).getValue() < 10 && !finalPrint){
            System.out.println("Dealer: [" + getCardsOnHand().get(0).toString() + ", SECRET CARD]");
        } else {
            System.out.println("Dealer: " + getCardsOnHand().toString());
        }
    }

    public void play(Deck deck, CardManipulator manipulator){
        while (this.getActualCount() < 17){
            System.out.println("\nDealer must hit.");
            manipulator.hit(this,deck);
            printCardsOfPlayer(true);
        }
    }
}
