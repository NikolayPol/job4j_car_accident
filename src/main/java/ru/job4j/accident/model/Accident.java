package ru.job4j.accident.model;

import lombok.*;

import java.util.Objects;
import java.util.Set;

/**
 * Класс Accident - модель правонарушения(инцидента).
 *
 * @author Nikolay Polegaev
 * @version 1.1 21.11.2021
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Accident {
    private int id;
    private String name;
    private String text;
    private String address;
    private AccidentType type;
    private Set<Rule> rules;

    public Accident(String name, String text, String address) {
        this.id = 0;
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public Accident(String name, AccidentType type, String text, String address) {
        this.id = 0;
        this.name = name;
        this.type = type;
        this.text = text;
        this.address = address;
    }

    public Accident(String name, AccidentType type, String text, String address, Set<Rule> rules) {
        this.id = 0;
        this.name = name;
        this.type = type;
        this.text = text;
        this.address = address;
        this.rules = rules;
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

    @Override
    public String toString() {
        return "Accident{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", address='" + address + '\''
                + ", accidentType='" + type + '\''
                + '}';
    }
}
