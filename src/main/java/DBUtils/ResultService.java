package DBUtils;

import DBUtils.HibernateUtils;
import GameEntities.Result;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by Simi on 23.9.2018.
 */
public class ResultService {

    public ResultService() {
    }

    public static Result getResultById(Long id) {
        Result result;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            result = session.get(Result.class, id);
        }
        return result;
    }

    public static List<Result> getResults() {
        List<Result> results;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            results = session.createQuery("from Result").list();
        }
        return results;
    }

    public static void save(Result result) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(result);
            session.getTransaction().commit();
        }
    }
}
