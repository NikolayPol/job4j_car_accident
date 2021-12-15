package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentHibernate;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.List;

/**
 * Класс AccidentService - описывает логику работы.
 *
 * @author Nikolay Polegaev
 * @version 2.0 15.12.2021
 */
@Service
public class AccidentService {
    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentService(AccidentRepository ar,
                           AccidentTypeRepository atr,
                           RuleRepository rr
                           ) {
        this.accidentRepository = ar;
        this.accidentTypeRepository = atr;
        this.ruleRepository = rr;
    }

    public List<Accident> getAllAccidents() {
        return accidentRepository.findAll();
    }

    public void saveOrUpdate(Accident accident, String[] ids) {
        for (String id : ids) {
            Rule rule = ruleRepository.findById(Integer.parseInt(id)).get();
            accident.addRule(rule);
        }
        AccidentType type = accidentTypeRepository.
                findById(accident.getType().getId()).get();
        accident.setType(type);
        accidentRepository.save(accident);
    }

    public Accident findAccidentById(int id) {
        return accidentRepository.findById(id).get();
    }

    public List<AccidentType> getAllTypes() {
        return accidentTypeRepository.findAll();
    }

    public AccidentType findTypeById(int id) {
        return accidentTypeRepository.findById(id).get();
    }

    public List<Rule> getAllRules() {
        return ruleRepository.findAll();
    }
}
