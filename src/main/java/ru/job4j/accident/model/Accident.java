package ru.job4j.accident.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * Класс Accident - модель правонарушения(инцидента).
 *
 * @author Nikolay Polegaev
 * @version 3.0 14.12.2021
 */
@Entity
@Table(name = "accident")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id")
    private AccidentType type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "accident_rule",
            joinColumns = @JoinColumn(name = "accident_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id"))
    private Set<Rule> rules;

    public Accident(String name, String text, String address) {
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public Accident(String name, AccidentType type, String text, String address) {
        this.name = name;
        this.type = type;
        this.text = text;
        this.address = address;
    }

    public Accident(String name, AccidentType type, String text, String address, Set<Rule> rules) {
        this.name = name;
        this.type = type;
        this.text = text;
        this.address = address;
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        if (rules == null) {
            rules = new HashSet<>();
        }
        rules.add(rule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
