package ru.job4j.accident;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import javax.servlet.ServletContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.job4j.accident.config.WebConfig;
import javax.servlet.ServletRegistration;

/**
 * Класс WebInit - расширяет WebApplicationInitializer.
 * Когда tomcat загружает наше приложение, он ищет класс,
 * который расширяет WebApplicationInitializer.
 *
 * Tomcat создает контекст Spring и загружает DispatcherServlet.
 * WebApplicationInitializer - замена web.xml.
 *
 * Servlet 3 и выше могут запускаться без web.xml.
 * Он использует конфигурирование через Java классы.
 *
 * @author Nikolay Polegaev
 * @version 1.0 21.11.2021
 */
public class WebInit implements WebApplicationInitializer {

    public void onStartup(ServletContext servletCxt) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(WebConfig.class);
        ac.refresh();
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
