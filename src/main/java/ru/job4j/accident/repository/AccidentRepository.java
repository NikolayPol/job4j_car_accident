package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Accident;

/**
 * Класс AccidentRepository
 *
 * @author Nikolay Polegaev
 * @version 1.0 15.12.2021
 */
public interface AccidentRepository extends JpaRepository<Accident, Integer> {
}
