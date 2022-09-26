package pl.coderslab.diywithspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;
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



//widocznosc plikow js w folderze /resources/js/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

}
