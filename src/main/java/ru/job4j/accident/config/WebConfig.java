package ru.job4j.accident.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Класс WebConfig
 * Аннотация ComponentScan. Она сканирует проект и загружает бины в контекст.
 * Внутри этого класса создается объект ViewResolver.
 * Spring использует этот объект для поиска jsp.
 * В нем сразу подключен JSTL.
 *
 * @author Nikolay Polegaev
 * @version 1.0 21.11.2021
 */
@Configuration
@ComponentScan("ru.job4j.accident")
public class WebConfig {
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver bean = new InternalResourceViewResolver();
        bean.setViewClass(JstlView.class);
        bean.setPrefix("./WEB-INF/views/");
        bean.setSuffix(".jsp");
        return bean;
    }
}
