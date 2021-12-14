package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс AccidentJdbcTemplate - для работы с БД.
 *
 * @author Nikolay Polegaev
 * @version 1.0 12.12.2021
 */
public class AccidentJdbcTemplate {
    private final JdbcTemplate jdbc;

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void saveOrUpdate(Accident accident) {
        if (accident.getId() == 0) {
            save(accident);
        } else {
            update(accident);
        }
    }

    private void save(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement("INSERT INTO accident (name, text, address, type_id) "
                            + "VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.getRules().forEach(rule -> jdbc.update("INSERT INTO "
                + "accident_rule (accident_id, rule_id) "
                + "VALUES(?, ?)", keyHolder.getKeys().get("id"), rule.getId()));
    }

    private void update(Accident accident) {
        jdbc.update("UPDATE accident SET name = ?, text = ?, address = ?, type_id = ? WHERE id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getType().getId(),
                accident.getId());
        jdbc.update("DELETE FROM accident_rule WHERE accident_id =?", accident.getId());
        accident.getRules().forEach(rule -> jdbc.update("INSERT INTO "
                        + "accident_rule (accident_id, rule_id) VALUES(?, ?)",
                accident.getId(), rule.getId()));
    }

    public List<Accident> getAllAccidents() {
        Map<Integer, Integer> accAndtype = new HashMap<>();
        List<Accident> accidents = jdbc.query("SELECT * FROM accident", (res, row) -> {
            accAndtype.put(res.getInt("id"), res.getInt("accidentType"));
            return Accident.builder()
                    .id(res.getInt("id"))
                    .name(res.getString("name"))
                    .address(res.getString("address"))
                    .text(res.getString("text"))
                    .build();
        });
        accidents.forEach(accident -> accident.setType(findTypeById(
                accAndtype.get(accident.getId()))));
        accidents.forEach(accident -> accident.setRules(getRulesByAccidentId(
                accident.getId())));
//        return new HashSet<>(accidents);
        return new ArrayList<>(accidents);
    }

    public Accident findAccidentById(int id) {
        final int[] typeId = new int[1];
        Accident accident = jdbc
                .queryForObject("SELECT * FROM accident WHERE id = ?", (res, row) -> {
                    typeId[0] = res.getInt("accidentType");
                    return Accident.builder()
                            .id(res.getInt("id"))
                            .name(res.getString("name"))
                            .address(res.getString("address"))
                            .text(res.getString("text"))
                            .build();
                }, id);
        accident.setType(findTypeById(typeId[0]));
        accident.setRules(getRulesByAccidentId(accident.getId()));
        return accident;
    }

    public List<AccidentType> getAllTypes() {

        HashSet<AccidentType> hset = new HashSet<AccidentType>(jdbc.query("SELECT * FROM type",
                (res, row) -> AccidentType.builder()
                        .id(res.getInt("id"))
                        .name(res.getString("name"))
                        .build()));
        return new ArrayList<>(hset);

    }

    public AccidentType findTypeById(int id) {
        return jdbc.queryForObject("SELECT * FROM type WHERE id = ?",
                (res, row) -> AccidentType.builder()
                        .id(res.getInt("id"))
                        .name(res.getString("name"))
                        .build(), id);
    }

    public List<Rule> getAllRules() {
        HashSet<Rule> hset = new HashSet<Rule>(jdbc.query("SELECT * FROM rule",
                (res, row) -> Rule.builder()
                .id(res.getInt("id"))
                .name(res.getString("name"))
                .build()));
        return new ArrayList<>(hset);
    }

    public Rule findRuleById(int id) {
        return jdbc.queryForObject("SELECT * FROM rule WHERE id = ?", (res, row) -> Rule.builder()
                .id(res.getInt("id"))
                .name(res.getString("name"))
                .build(), id);
    }

    private Set<Rule> getRulesByAccidentId(int id) {
        List<Integer> ruleIds = jdbc.query("SELECT * FROM accident_rule WHERE accident_id =?",
                (res, row) -> res.getInt("rule_id"), id);
        return ruleIds.stream().map(integer -> jdbc
                .queryForObject("SELECT * FROM rule WHERE id = ?", (res, row) -> Rule.builder()
                        .id(res.getInt("id"))
                        .name(res.getString("name"))
                        .build(), integer)).collect(Collectors.toSet());
    }
}
