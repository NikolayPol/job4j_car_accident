package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.accident.model.User;

/**
 * Класс UserRepository
 *
 * @author Nikolay Polegaev
 * @version 1.0 15.12.2021
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
