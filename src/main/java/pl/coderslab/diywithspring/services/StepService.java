package pl.coderslab.diywithspring.services;

import pl.coderslab.diywithspring.models.Step;

import java.util.List;

public interface StepService {

    void saveStep(Step step);
    List getStepList(Long projectID);

}
