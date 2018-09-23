import Authorities.CardManipulator;
import Authorities.GameModerator;
import DBEntities.Round;
import DBUtils.HibernateUtils;
import DBUtils.ResultService;
import DBUtils.RoundService;
import GameEntities.Dealer;
import GameEntities.Deck;
import GameEntities.Player;
import DBEntities.Result;
import org.hibernate.Session;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * Author: SimRau
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        String input = "N";
        String connectionURL = "jdbc:derby:memory:blackjack;create=true";
        Connection conn = DriverManager.getConnection(connectionURL);
        int counter = 0;
        GameModerator moderator = new GameModerator();
        int decksCount = moderator.initializeDecksCount();
        List<Player> players = moderator.initializePlayers();

        while (input.toUpperCase().equals("N")) {
            counter++;
            Round round = new Round(counter,1);

            Deck deck = CardManipulator.initializeRound(decksCount,players,moderator);
            List<Result> results = playGame(moderator,players,deck);

            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            ResultService.saveAllResults(results, counter, 1);
            RoundService.save(round);

            session.getTransaction().commit();
            System.out.println("If you would like to play " +
                    "new game with the same deck count and players input N and press ENTER!");
            input = new Scanner(System.in).nextLine();
        }
        
        HibernateUtils.shutdown();
    }

    public static List<Result> playGame(GameModerator moderator, List<Player> players, Deck deck){
        if (moderator.getDealer().hasBlackJack()) {
            System.out.println("Sorry, dealer has a blackjack. You all have lost. \nGame over!");
        } else {
            moderator.manageGame(players, deck);
            moderator.printCardsOfAllPlayers(players, true);

            Dealer dealer = (Dealer) moderator.getDealer();
            dealer.play(deck);
            System.out.println("Dealer has reached 17. \n");
        }
        List<Result> results = moderator.printFinalScore(players);
        System.out.println("\nGame over!");
        return results;
    }
}
