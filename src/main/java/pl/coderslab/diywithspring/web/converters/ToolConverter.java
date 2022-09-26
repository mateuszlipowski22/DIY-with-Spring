package pl.coderslab.diywithspring.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.diywithspring.models.Tool;
import pl.coderslab.diywithspring.services.interfaces.ToolService;

public class ToolConverter implements Converter<String, Tool> {
    @Autowired
    private ToolService toolService;
 
    @Override
    public Tool convert(String source) {
        return toolService.getToolByID(Long.parseLong(source));
    }
}
