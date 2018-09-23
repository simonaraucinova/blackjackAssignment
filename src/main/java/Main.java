import Authorities.CardManipulator;
import Authorities.GameModerator;
import DBUtils.HibernateUtils;
import DBUtils.ResultService;
import GameEntities.Dealer;
import GameEntities.Deck;
import GameEntities.Player;
import GameEntities.Result;
import org.apache.derby.jdbc.EmbeddedDataSource;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Simi on 21.9.2018.
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        GameModerator moderator = new GameModerator();
        Deck deck = new Deck(moderator.initializeDecksCount());
        List<Player> players = moderator.initializePlayers();

        CardManipulator manipulator = new CardManipulator();
        manipulator.dealCards(deck, players);
        moderator.printCardsOfAllPlayers(players, false);

        if(moderator.getDealer().hasBlackJack()){
            System.out.println("Sorry, dealer has a blackjack. You all have lost. \nGame over!");
            System.exit(0);
        }


        moderator.manageGame(players, deck);
        moderator.printCardsOfAllPlayers(players,true);

        Dealer dealer = (Dealer) moderator.getDealer();
        dealer.play(deck,manipulator);
        System.out.println("Dealer has reached 17. \n");

        List<Result> results = moderator.printFinalScore(players);
        System.out.println("\nGame over!");


        String connectionURL = "jdbc:derby:memory:blackjack;create=true";
        Connection conn = DriverManager.getConnection(connectionURL);

        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        for(Result result: results) {
            ResultService.save(result);
        }

        List<Result> resultFinal = ResultService.getResults();
        for(Result result1: results){
            System.out.println(result1);
        }
        session.getTransaction().commit();
        HibernateUtils.shutdown();
    }
}
