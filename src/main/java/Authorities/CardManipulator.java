package Authorities;

import GameEntities.Card;
import GameEntities.Deck;
import GameEntities.Player;

import java.util.List;

/**
 * Created by Simi on 22.9.2018.
 */
public class CardManipulator {

    public void dealCards(Deck deck, List<Player> playerList){
        System.out.println("\nCards are dealt...");
        for (int i = 0; i < 2 * playerList.size(); i++){
            Player player = playerList.get(i % playerList.size());
            Card card = deck.popCard();
            player.getCardsOnHand().add(card);
            player.setActualCount(player.getActualCount() + card.getValue());

        }
    }

    public void hit(Player player, Deck deck){
        Card card = deck.popCard();
        player.getCardsOnHand().add(card);
        player.setActualCount(player.getActualCount() + card.getValue());
    }

    public void checkTrop(Player player){
        if (player.getActualCount() > 21){
            Card ace;
            if ((ace = player.getAceOnHand()) != null){
                ace.setValue(1);
                player.setActualCount(player.getActualCount() - 10);
            } else {
                player.printCardsOfPlayer();
                System.out.println(player.getName() + ", you have trop. Unfortunately, you have lost :(");
                player.setStanding(true);
            }
        }
    }


}
