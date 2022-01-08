package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.List;

/**
 * Класс AccidentService - описывает логику работы.
 *
 * @author Nikolay Polegaev
 * @version 1.1 14.12.2021
 */
@Service
public class AccidentService {
    private final AccidentJdbcTemplate accidentRepository;

    public AccidentService(AccidentJdbcTemplate store) {
        this.accidentRepository = store;
    }

    public List<Accident> getAllAccidents() {
        return accidentRepository.getAllAccidents();
    }

    public void saveOrUpdate(Accident accident, String[] ids) {
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
