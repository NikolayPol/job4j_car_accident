package ru.job4j.accident.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * Класс Rule - описывает статьи нарушений
 *
 * @author Nikolay Polegaev
 * @version 1.0 12.12.2021
 */
@Entity
@Table(name = "rule")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public static Rule of(int id, String name) {
        Rule rule = new Rule();
        rule.id = id;
        rule.name = name;
        return rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Rule rule = (Rule) o;
        return id == rule.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
