package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;
import java.util.List;

/**
 * Класс AccidentService - описывает логику работы.
 * Хранилище должно иметь аннотацию @Repository.
 *
 * @author Nikolay Polegaev
 * @version 1.0 21.11.2021
 */
@Service
public class AccidentService {
    private final AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public List<Accident> getAllAccidents() {
        return store.getAllAccidents();
    }

    public void addAccident(Accident accident) {
        accident.setId(0);
        store.addAccident(accident);
    }
}
