package Authorities;

import GameEntities.Card;
import GameEntities.Deck;
import GameEntities.Player;

import java.util.List;

/**
 * Represents entity which manipulates with cards.
 *
 * Author: SimRau
 */
public class CardManipulator {

    /**
     * Deals cards to players.
     *
     * @param deck actual deck
     * @param playerList actual list of players
     */
    public static void dealCards(Deck deck, List<Player> playerList){
        System.out.println("\nCards are dealt...");
        cleanUpPlayer(playerList);
        for (int i = 0; i < 2 * playerList.size(); i++){
            Player player = playerList.get(i % playerList.size());
            Card card = deck.popCard();
            player.getCardsOnHand().add(card);
            player.setActualCount(player.getActualCount() + card.getValue());

        }
    }

    private static void cleanUpPlayer(List<Player> playerList){
        for (Player player: playerList){
            player.getCardsOnHand().clear();
            player.setActualCount(0);
            player.setStanding(false);
            player.setSurrender(false);
        }
    }

    /**
     * Player hit.
     *
     * @param player player who hits
     * @param deck actual deck
     */
    public static void hit(Player player, Deck deck){
        Card card = deck.popCard();
        player.getCardsOnHand().add(card);
        player.setActualCount(player.getActualCount() + card.getValue());
    }

    /**
     * Check if player has not trop.
     *
     * @param player player to be checked
     */
    public static void checkTrop(Player player){
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

    /**
     * Initializes new round.
     *
     * @param decksCount number of 52-card decks to use in game
     * @param players list of actual players
     * @param moderator moderator of game
     * @return
     */
    public static Deck initializeRound(int decksCount, List<Player> players, GameModerator moderator){
        Deck deck = new Deck(decksCount);
        for(Player player : players){
            if (!player.getName().equals("Dealer")) {
                player.setBet(moderator.initializeBet(player));
            }
        }
        dealCards(deck, players);
        moderator.printCardsOfAllPlayers(players, false);
        return deck;
    }


}
