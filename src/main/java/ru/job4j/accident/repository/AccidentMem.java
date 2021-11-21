package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс AccidentMem - хранилище инцидентов.
 * Хранилище должно иметь аннотацию @Repository.
 *
 * @author Nikolay Polegaev
 * @version 1.0 21.11.2021
 */
@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();

    private AccidentMem() {
        accidents.put(1, Accident.builder()
                .id(1)
                .name("Столкновение")
                .address("Москва, Комсомольский пр-кт, д.1")
                .text("Столкновение двух машин").build());
        accidents.put(2, Accident.builder()
                .id(2)
                .name("Неправильная парковка")
                .address("Москва, Комсомольский пр-кт, д.2")
                .text("Номер машины аб123в45 непавильная парковка").build());
        accidents.put(3, Accident.builder()
                .id(3)
                .name("Наезд на пешехода")
                .address("Москва, Комсомольский пр-кт, д.3")
                .text("Номер машины аб234в45").build());
    }

    public List<Accident> getAllAccidents() {
        return new ArrayList<>(accidents.values());
    }
}
