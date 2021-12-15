package ru.job4j.accident;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Класс SecurityInit
 * Этот класс подключает DelegatingFilterProxy.
 * Это главный фильтр, в котором идет обработка запросов.
 * Tomcat автоматически определяет такой класс и подключает фильтры.
 *
 * @author Nikolay Polegaev
 * @version 1.0 15.12.2021
 */
public class SecurityInit extends AbstractSecurityWebApplicationInitializer {
}
