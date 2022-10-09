package pl.coderslab.diywithspring.dto;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.diywithspring.models.Project;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ComponentDTO {

    private Long id;

    private Project project;

    private String description;

    @Min(value = 1)
    @NotNull
    private Integer quantity;

}
