package siwproject.siwproject.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import siwproject.siwproject.form.AlbumForm;
import siwproject.siwproject.model.Album;

@Component
public class AlbumValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return AlbumForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeFotografo", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeAlbum", "required");
    }

}