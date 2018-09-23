package DBUtils;

import DBEntities.Round;
import org.hibernate.Session;

import java.util.List;

/**
 * Represents the basic round service.
 *
 * Author: SimRau
 */
public class RoundService {

    public RoundService() {
    }

    /**
     * Retrieves round from DB by id.
     *
     * @param id round id
     * @return round with given id
     */
    public static Round getRoundById(Long id) {
        Round round;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            round = session.get(Round.class, id);
        }
        return round;
    }

    /**
     * Retrieves all rounds.
     *
     * @return list of rounds
     */
    public static List<Round> getRounds() {
        List<Round> rounds;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            rounds = session.createQuery("from Round").list();
        }
        return rounds;
    }

    /**
     * Saves round into DB.
     *
     * @param round round to be saved
     */
    public static void save(Round round) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(round);
            session.getTransaction().commit();
        }
    }

    /**
     * Saves all rounds belonging to the game.
     *
     * @param rounds list of rounds to be saved
     * @param gameNumber number of game
     */
    public static void saveAllRounds(List<Round> rounds, int gameNumber){
        for (Round round : rounds){
            round.setGameNumber(gameNumber);
            save(round);
        }
    }
}
