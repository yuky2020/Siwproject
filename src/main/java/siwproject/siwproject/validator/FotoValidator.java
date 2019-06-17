package siwproject.siwproject.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import siwproject.siwproject.form.FotoForm;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.repository.AlbumRepository;
import siwproject.siwproject.repository.FotoRepository;
import siwproject.siwproject.repository.FotografoRepository;

@Component
public class FotoValidator implements Validator {

    @Autowired
    private FotografoRepository fotografoRepository;
    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return FotoForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FotoForm form = (FotoForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "files", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeFotografo", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeAlbum", "required");
        if (!albumRepository.existsByNome(form.getNomeAlbum()) && !form.getNomeAlbum().equals("")) {
            errors.rejectValue("nomeAlbum", "invalid");
        }
        if (!fotografoRepository.existsByNome(form.getNomeFotografo()) && !form.getNomeFotografo().equals("")) {
            errors.rejectValue("nomeFotografo", "invalid");
        }
        if (form.getHashTagList().length() > 250) {
            errors.rejectValue("hashTagList", "long");
        }
    }
}