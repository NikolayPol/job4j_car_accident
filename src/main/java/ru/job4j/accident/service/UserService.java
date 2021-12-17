package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

/**
 * Класс UserService
 *
 * @author Nikolay Polegaev
 * @version 1.0 17.12.2021
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserByName(User user) {
        return userRepository.findByUsername(user.getUsername());
    }

}
