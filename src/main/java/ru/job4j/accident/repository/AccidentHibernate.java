package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Класс AccidentHibernate
 *
 * @author Nikolay Polegaev
 * @version 2.0 14.12.2021
 */
@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public <T> T sessionMethodsWithReturn(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void sessionVoidMethods(final Consumer<Session> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void saveOrUpdate(Accident accident) {
        sessionVoidMethods(session -> {
            AccidentType type = findTypeById(accident.getType().getId());
            accident.setType(type);
            session.saveOrUpdate(accident);
        });
    }

    public List<Accident> getAllAccidents() {
        return sessionMethodsWithReturn(session -> session.createQuery(
                "select distinct a from Accident a join fetch a.rules", Accident.class).list());
    }

    public Accident findAccidentById(int id) {
        return sessionMethodsWithReturn(session -> session.createQuery(
                "select distinct a from Accident a join fetch a.rules where a.id = :id",
                Accident.class
        ).setParameter("id", id).uniqueResult());
    }

    public List<AccidentType> getAllTypes() {
        return sessionMethodsWithReturn(session -> session.createQuery("from AccidentType").list());
    }

    public AccidentType findTypeById(int id) {
        return sessionMethodsWithReturn(session -> session.get(AccidentType.class, id));
    }

    public List<Rule> getAllRules() {
        return sessionMethodsWithReturn(session -> session.createQuery("from Rule").list());
    }

    public Rule findRuleById(int id) {
        return sessionMethodsWithReturn(session -> session.get(Rule.class, id));
    }
}
