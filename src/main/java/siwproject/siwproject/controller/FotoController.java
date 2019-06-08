package siwproject.siwproject.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import siwproject.siwproject.client.AmazonClient;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.pg.AlbumService;
import siwproject.siwproject.pg.FotoService;
import siwproject.siwproject.pg.FotografoService;
import siwproject.siwproject.validator.FotoValidator;

@Controller
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
        return "newFoto";
    }
//rimosso link qui perche veniva duplicato
    @RequestMapping(value = "/foto", method = RequestMethod.POST)
    public String inserisciFoto(@Valid @ModelAttribute("foto") Foto foto, Model model, BindingResult bindingResults,
            @RequestPart(value = "file") MultipartFile file, @RequestParam("nomeFotografo") String nomeFotografo) {
        String url = "https://s3.eu-west-3" + this.amazonClient.uploadFile(file).substring(20);
        foto.setUrl(url);
        Fotografo fotografo = fotografoService.fotografoPerNome(nomeFotografo);
        foto.setFotografo(fotografo);
        fotoValidator.validate(foto, bindingResults);
        if (!bindingResults.hasErrors()) {
            fotografo.getFoto().add(foto);
            fotoService.inserisci(foto);
            model.addAttribute("fotoSuccess", "Foto inserita con successo");
            return "paginaAdmin";
        } else {
            this.amazonClient.deleteFileFromS3Bucket(url);
            return "newFoto";
        }
    }

    @RequestMapping(value = { "/mostraFoto" }, method = RequestMethod.GET)
    public String viewFoto(Model model) {
        List<Foto> foto = fotoService.tutte();
        model.addAttribute("fotos", foto);
        return "mostraFoto";
    }
}
