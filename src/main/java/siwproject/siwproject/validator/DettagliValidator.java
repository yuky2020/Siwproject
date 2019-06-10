package siwproject.siwproject.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import siwproject.siwproject.model.Dettagli;

@Component
public class DettagliValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Dettagli.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cellulare", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codicePay", "required");
    }

}