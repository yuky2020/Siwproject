package siwproject.siwproject.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import siwproject.siwproject.model.Foto;

@Component
public class FotoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Foto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "files", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeFotografo", "required");
        ValidationUtils.rejectIfEmpty(errors, "nomeAlbum", "required");
    }
}