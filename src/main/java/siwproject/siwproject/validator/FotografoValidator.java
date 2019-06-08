package siwproject.siwproject.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.pg.FotografoService;

@Component
public class FotografoValidator implements Validator {

    @Autowired
    private FotografoService fotografoService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Fotografo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
        if (this.fotografoService.alreadyExists((Fotografo) target)) {
            errors.reject("duplicato");
        }
    }

}
