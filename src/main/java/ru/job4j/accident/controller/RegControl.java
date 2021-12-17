package ru.job4j.accident.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;
import ru.job4j.accident.service.UserService;

/**
 * Класс RegControl - контроллер для регистрации.
 *
 * @author Nikolay Polegaev
 * @version 1.0 15.12.2021
 */
@Controller
public class RegControl {
    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;
    private final UserService userService;

    public RegControl(PasswordEncoder encoder, UserRepository users,
                      AuthorityRepository authorities, UserService userService) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
        this.userService = userService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        User userRegistered = userService.findUserByName(user);
        if (userRegistered != null && user.getUsername().equals(userRegistered.getUsername())) {
            model.addAttribute("userRegistered", userRegistered);
            return "reg";
        } else {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorities.findByAuthority("ROLE_USER"));
            users.save(user);
            return "redirect:/login";
        }
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
