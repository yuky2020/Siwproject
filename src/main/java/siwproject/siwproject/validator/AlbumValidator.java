package siwproject.siwproject.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import siwproject.siwproject.form.AlbumForm;
import siwproject.siwproject.pg.AlbumService;
import siwproject.siwproject.pg.FotografoService;

@Component
public class AlbumValidator implements Validator {

    @Autowired
    private FotografoService fotografoService;
    @Autowired
    private AlbumService albumService;

    @Override
    public boolean supports(Class<?> clazz) {
        return AlbumForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AlbumForm form = (AlbumForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeFotografo", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeAlbum", "required");
        if (form.getHashTagList().length() > 250) {
            errors.rejectValue("hashTagList", "long");
        }

        if (!fotografoService.existsByNome(form.getNomeFotografo()) && !form.getNomeFotografo().equals("")) {
            errors.rejectValue("nomeFotografo", "invalid");
        }
    }

}