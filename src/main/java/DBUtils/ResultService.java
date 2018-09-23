package DBUtils;

import DBUtils.HibernateUtils;
import GameEntities.Result;
import org.hibernate.Session;

import java.util.List;

/**
 * Rapresents the basic result service.
 *
 * Author: SimRau
 */
public class ResultService {

    public ResultService() {
    }

    /**
     * Retrieves result from DB by id.
     *
     * @param id result id
     * @return result with given id
     */
    public static Result getResultById(Long id) {
        Result result;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            result = session.get(Result.class, id);
        }
        return result;
    }

    /**
     * Retrieves all results.
     *
     * @return list of results
     */
    public static List<Result> getResults() {
        List<Result> results;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            results = session.createQuery("from Result").list();
        }
        return results;
    }

    /**
     * Saves result into DB.
     *
     * @param result result to be saved
     */
    public static void save(Result result) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(result);
            session.getTransaction().commit();
        }
    }

    /**
     * Saves all results belonging to the game.
     *
     * @param results list of results to be saved
     * @param gameNumber number of game
     */
    public static void saveAllResults(List<Result> results, int gameNumber){
        for (Result result : results){
            result.setGameNumber(gameNumber);
            save(result);
        }
    }
}
