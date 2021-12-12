package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;

import java.util.List;

/**
 * Класс AccidentService - описывает логику работы.
 *
 * @author Nikolay Polegaev
 * @version 1.0 12.12.2021
 */
@Service
public class AccidentService {
    private final AccidentHibernate accidentRepository;

    public AccidentService(AccidentHibernate store) {
        this.accidentRepository = store;
    }

    public List<Accident> getAllAccidents() {
        return accidentRepository.getAllAccidents();
    }

    public void saveOrUpdate(Accident accident, String[] ids) {
            AccidentType type = accidentRepository.findTypeById(accident.getType().getId());
            accident.setType(type);
            for (String id : ids) {
                Rule rule = accidentRepository.findRuleById(Integer.parseInt(id));
                accident.addRule(rule);
            }
            accidentRepository.saveOrUpdate(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentRepository.findAccidentById(id);
    }

    public List<AccidentType> getAllTypes() {
        return accidentRepository.getAllTypes();
    }

    public AccidentType findTypeById(int id) {
        return accidentRepository.findTypeById(id);
    }

    public List<Rule> getAllRules() {
        return accidentRepository.getAllRules();
    }
}
