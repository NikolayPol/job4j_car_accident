package ru.job4j.accident.repository;

import org.apache.commons.digester.Rules;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс AccidentMem - хранилище инцидентов.
 * Хранилище должно иметь аннотацию @Repository.
 *
 * @author Nikolay Polegaev
 * @version 1.1 21.11.2021
 */
@Repository
public class AccidentMem {

    private static final AtomicInteger ACC_ID = new AtomicInteger(3);
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final Map<Integer, AccidentType> types = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();

    private AccidentMem() {
        AccidentType type1 = AccidentType.builder().id(1).name("Две машины").build();
        AccidentType type2 = AccidentType.builder().id(2).name("Машина и человек").build();
        AccidentType type3 = AccidentType.builder().id(3).name("Машина и велосипед").build();
        types.put(1, type1);
        types.put(2, type2);
        types.put(3, type3);

        Rule rule1 = Rule.builder().id(1).name("Статья 1").build();
        Rule rule2 = Rule.builder().id(2).name("Статья 2").build();
        Rule rule3 = Rule.builder().id(3).name("Статья 3").build();
        rules.put(1, rule1);
        rules.put(2, rule2);
        rules.put(3, rule3);

        accidents.put(1, Accident.builder()
                .id(1)
                .name("Столкновение")
                .type(types.get(1))
                .rules(Set.of(rules.get(1), rules.get(2)))
                .address("Москва, Комсомольский пр-кт, д.1")
                .text("Столкновение двух машин").build());
        accidents.put(2, Accident.builder()
                .id(2)
                .name("Неправильная парковка")
                .type(types.get(2))
                .rules(Set.of(rules.get(1), rules.get(3)))
                .address("Москва, Комсомольский пр-кт, д.2")
                .text("Номер машины аб123в45 непавильная парковка").build());
        accidents.put(3, Accident.builder()
                .id(3)
                .name("Наезд на пешехода")
                .type(types.get(2))
                .rules(Set.of(rules.get(2)))
                .address("Москва, Комсомольский пр-кт, д.3")
                .text("Номер машины аб234в45").build());
    }

    public List<Accident> getAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    public void addAccident(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(ACC_ID.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public List<AccidentType> getAllAccidentTypes() {
        return new ArrayList<>(types.values());
    }

    public AccidentType findTypeById(int id) {
        return types.get(id);
    }

    public List<Rule> getAllRules() {
        return new ArrayList<>(rules.values());
    }

    public Set<Rule> getRules(String[] ids) {
        Set<Rule> result = new HashSet<>();
        for (String id : ids) {
            result.add(rules.get(Integer.parseInt(id)));
        }
        return result;
    }

    public Rule findRuleById(int id) {
        return rules.get(id);
    }

}
