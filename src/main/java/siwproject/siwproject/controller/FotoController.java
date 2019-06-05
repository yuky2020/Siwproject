package siwproject.siwproject.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.Part;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import siwproject.siwproject.client.AmazonClient;
import siwproject.siwproject.controller.*;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.pg.AlbumService;
import siwproject.siwproject.pg.FotoService;
import siwproject.siwproject.pg.FotografoService;
import siwproject.siwproject.validator.FotoValidator;

public class FotoController {
    @Autowired
    private FotoService fotoService;
    @Autowired
    private FotoValidator fotoValidator;
    @Autowired
    private FotografoService fotografoService;
    @Autowired
    AlbumService albumService;

    private AmazonClient amazonClient;

    @Autowired
    FotoController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @RequestMapping("/aggiungiFoto")
    public String aggiungiFoto(Model model) {
        model.addAttribute("newFoto", new Foto());
        return "formFoto";
    }

    @RequestMapping(value = "/foto", method = RequestMethod.POST)
    public String inserisciFoto(@Valid @ModelAttribute("foto") Foto foto, Model model, BindingResult bindingResult,
            @RequestPart(value = "file") MultipartFile file, @RequestParam("fotografo") String fotografo,
            @RequestParam("album") String album) {
        String link = this.amazonClient.uploadFile(file);
        String linkb = "https://silph.s3.eu-west-3.amazonaws.com" + link.substring(40);
        foto.setUrl(linkb);
        foto.setAlbum(this.albumService.albumPerNome(album));
        foto.setFotografo(this.fotografoService.fotografoPerNome(fotografo));
        this.fotoValidator.validate(foto, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.fotoService.inserisci(foto);
            model.addAttribute("fotoSuccess", "Foto inserita con successo");
            return "console";
        } else {
            return "formfoto";
        }

    }
}
