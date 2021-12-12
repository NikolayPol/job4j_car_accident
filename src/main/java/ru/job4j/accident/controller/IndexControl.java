package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;
import ru.job4j.accident.service.AccidentService;
import org.springframework.ui.Model;

import java.util.List;


/**
 * Класс IndexControl - контроллер для главной страницы indexMem.jsp.
 * Отмечено аннотацией @Controller.
 * Аннотации @Repository, @Service, @Controller представляют собой
 * частые случаи аннотации @Component.
 *
 * Если класс отмечен аннотацией @Component,
 * то Spring регистрирует этот класс в контексте.
 *
 * Spring сканирует проект и регистрирует этот контроллер.
 * Когда на Dispacher приходит запрос, он ищет подходящий контроллер.
 *
 * Метод index возвращает имя вида без расширения.
 *
 * @author Nikolay Polegaev
 * @version 1.0 21.11.2021
 */
@Controller
public class IndexControl {
    private final AccidentJdbcTemplate accidents;

    public IndexControl(AccidentJdbcTemplate accidents) {
        this.accidents = accidents;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("accidents", accidents.getAll());
        return "index";
    }
}
