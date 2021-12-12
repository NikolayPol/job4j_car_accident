package ru.job4j.accident;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

import org.springframework.web.filter.CharacterEncodingFilter;
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
 * filter - фильтр для кодировки.
 *
 * CharacterEncodingFilter будет преобразовывать текст в кодировку UTF-8.
 * Без этого кириллиц будет выглядеть крякозаброй.
 *
 * @author Nikolay Polegaev
 * @version 1.1 12.12.2021
 */
public class WebInit implements WebApplicationInitializer {

    public void onStartup(ServletContext servletCxt) {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(WebConfig.class);
        ac.refresh();

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        filter.setForceRequestEncoding(true);
        FilterRegistration.Dynamic encoding = servletCxt.addFilter("encoding", filter);
        encoding.addMappingForUrlPatterns(null, false, "/*");

        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletCxt.addServlet("app", servlet);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
