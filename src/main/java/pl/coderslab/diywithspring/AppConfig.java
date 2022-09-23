package pl.coderslab.diywithspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.diywithspring.web.converters.ToolConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.coderslab")

public class AppConfig implements WebMvcConfigurer {


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/",".jsp");
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(getToolConverter());
    }
    @Bean
    public ToolConverter getToolConverter() {
        return new ToolConverter();
    }
}
