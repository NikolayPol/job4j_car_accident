package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.List;

/**
 * Класс AccidentHibernate
 *
 * @author Nikolay Polegaev
 * @version 1.0 12.12.2021
 */
@Repository
public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public void saveOrUpdate(Accident accident) {
        try (Session session = sf.openSession()) {
            session.saveOrUpdate(accident);
        }
    }

    public List<Accident> getAllAccidents() {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select distinct a from Accident a join fetch a.rules", Accident.class).list();
        }
    }

    public Accident findAccidentById(int id) {
        try (Session session = sf.openSession()) {
            return session.createQuery(
                    "select distinct a from Accident a join fetch a.rules where a.id = :id",
                    Accident.class
            ).setParameter("id", id).uniqueResult();
        }
    }

    public List<AccidentType> getAllTypes() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from AccidentType").list();
        }
    }

    public AccidentType findTypeById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(AccidentType.class, id);
        }
    }

    public List<Rule> getAllRules() {
        try (Session session = sf.openSession()) {
            return session.createQuery("from Rule").list();
        }
    }

    public Rule findRuleById(int id) {
        try (Session session = sf.openSession()) {
            return session.get(Rule.class, id);
        }
    }
}
