package ru.job4j.accident.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;


/**
 * Класс AccidentControl - контроллер для create.jsp
 *
 * @author Nikolay Polegaev
 * @version 1.1 12.12.2021
 */
@Controller
public class AccidentControl {
    private final AccidentService accidentService;

    @Autowired
    public AccidentControl(AccidentService accidentService) {
        this.accidentService = accidentService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", accidentService.getAllTypes());
        model.addAttribute("rules", accidentService.getAllRules());
        return "accident/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        AccidentType type = accidentService.findTypeById(accident.getType().getId());
        accident.setType(type);
        String[] ids = req.getParameterValues("rIds");
        accidentService.saveOrUpdate(accident, ids);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidentService.getAllTypes());
        model.addAttribute("accident", accidentService.findAccidentById(id));
        return "accident/update";
    }
}
