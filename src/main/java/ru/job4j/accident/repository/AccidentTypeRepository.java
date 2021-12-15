package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

/**
 * Класс TypeRepository
 * Класс с тестами
 *
 * @author Nikolay Polegaev
 * @version 1.0 15.12.2021
 */
public interface AccidentTypeRepository extends JpaRepository<AccidentType, Integer> {
}
