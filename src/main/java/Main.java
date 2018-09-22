import Authorities.CardManipulator;
import Authorities.GameModerator;
import GameEntities.Dealer;
import GameEntities.Deck;
import GameEntities.Player;

import java.io.IOException;
import java.util.List;

/**
 * Created by Simi on 21.9.2018.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        GameModerator moderator = new GameModerator();
        Deck deck = new Deck(moderator.initializeDecksCount());
        List<Player> players = moderator.initializePlayers();

        CardManipulator manipulator = new CardManipulator();
        manipulator.dealCards(deck, players);

        if(moderator.getDealer().hasBlackJack()){
            System.out.println("Sorry, dealer has a blackjack. You all have lost. \nGame over!");
            System.exit(0);
        }

        moderator.printCardsOfAllPlayers(players, false);
        moderator.manageGame(players, deck);
        moderator.printCardsOfAllPlayers(players,true);

        Dealer dealer = (Dealer) moderator.getDealer();
        dealer.play(deck,manipulator);
        System.out.println("Dealer has reached 17. \n");

        moderator.printFinalScore(players);
        System.out.println("\nGame over!");
    }
}
