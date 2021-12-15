package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

/**
 * Класс RuleRepository
 *
 * @author Nikolay Polegaev
 * @version 1.0 15.12.2021
 */
public interface RuleRepository extends JpaRepository<Rule, Integer> {
}
