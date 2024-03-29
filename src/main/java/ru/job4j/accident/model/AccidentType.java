package ru.job4j.accident.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс AccidentType - описывает тип инцидента
 *
 * @author Nikolay Polegaev
 * @version 1.0 12.12.2021
 */
@Entity
@Table(name = "type")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccidentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public static AccidentType of(int id, String name) {
        AccidentType type = new AccidentType();
        type.id = id;
        type.name = name;
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccidentType that = (AccidentType) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
