package pl.coderslab.diywithspring.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword,String> {
    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {


        List<Character> characterListOfValue = new ArrayList<>();

        for (char ch: value.toCharArray()) {
            characterListOfValue.add(ch);
        }

        if(value.length()<8){
            return false;
        }
        if(value.length()>30){
            return false;
        }

        if(characterListOfValue.stream().filter(character -> Character.isLowerCase(character)).findFirst().isEmpty()){
            return false;
        };

        if(characterListOfValue.stream().filter(character -> Character.isUpperCase(character)).findFirst().isEmpty()){
            return false;
        };

        if(characterListOfValue.stream().filter(character -> Character.isDigit(character)).findFirst().isEmpty()){
            return false;
        };

        if(characterListOfValue.stream().filter(character -> Character.isWhitespace(character)).findFirst().isPresent()){
            return false;
        };

        return true;
    }
}
