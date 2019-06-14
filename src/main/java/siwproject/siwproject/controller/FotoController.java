package siwproject.siwproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.Generated;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import siwproject.siwproject.client.AmazonClient;
import siwproject.siwproject.form.FotoForm;
import siwproject.siwproject.form.HashTagForm;
import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.model.HashTag;
import siwproject.siwproject.pg.AlbumService;

import siwproject.siwproject.pg.FotoService;
import siwproject.siwproject.pg.FotografoService;
import siwproject.siwproject.pg.HashTagService;
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
    private AlbumService albumService;
    @Autowired
    private HashTagService hashTagService;

    private AmazonClient amazonClient;

    @Autowired
    FotoController(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @GetMapping("/aggiungiFoto")
    public String aggiungiFoto(Model model) {
        model.addAttribute("form", new FotoForm());
        model.addAttribute("fotografi", fotografoService.tutti());
        return "newFoto";
    }

    @PostMapping("/foto")
    public String inserisciFoto(@Valid @ModelAttribute("form") FotoForm form, Model model, BindingResult errors) {
        fotoValidator.validate(form, errors);
        if (!errors.hasErrors()) {
            Fotografo fotografo = fotografoService.fotografoPerNome(form.getNomeFotografo());
            Album album = albumService.albumPerNome(form.getNomeAlbum());
            List<HashTag> hashTags = parseHashTag(form.getHashTagList());
            for (MultipartFile file : form.getFiles()) {
                String url = "https://s3.eu-west-3" + this.amazonClient.uploadFile(file).substring(20);
                Foto foto = new Foto(url, fotografo, album);
                linkTags(foto, hashTags);
                fotoService.inserisci(foto);
            }
            return "paginaAdmin";
        } else {
            return "newFoto";
        }

    }

    @GetMapping("/mostraFoto")
    public String viewFoto(Model model) {
        List<Foto> foto = fotoService.ultime30();
        model.addAttribute("fotos", foto);
        return "mostraFoto";
    }

    public List<HashTag> parseHashTag(String hashTagString) {
        List<HashTag> hashTags = new ArrayList<HashTag>();
        Scanner sc = new Scanner(hashTagString);
        sc.useDelimiter("\\s*#\\s*");
        while (sc.hasNext()) {
            String tag = sc.next();
            if (!hashTagService.existsByNome(tag)) {
                hashTagService.inserisci(new HashTag(tag));
            }
            hashTags.add(hashTagService.hashTagPerNome(tag));
        }
        sc.close();
        return hashTags;
    }

    @GetMapping("/paginaFoto/{id}")
    public String paginaFoto(Model model, @PathVariable("id") long id) {
        Foto foto = fotoService.fotoPerId(id);
        model.addAttribute("foto", foto);
        return "paginaFoto";
    }

    public void linkTags(Foto foto, List<HashTag> HashTags) {
        for (HashTag hashTag : HashTags) {
            hashTag.addFoto(foto);
        }
    }

    @GetMapping("/aggiungiAlCarrello/{id}")
    public String aggiungiFotoAlCarrello(Model model, HttpSession session, @ModelAttribute("id") long id) {

        Foto foto = fotoService.fotoPerId(id);
        List<Foto> carrello;
        if (session.getAttribute("carrello") != null) {
            carrello = (List<Foto>) session.getAttribute("carrello");
        } else {
            carrello = new ArrayList<Foto>();
        }
        carrello.add(foto);
        model.addAttribute("foto", foto);
        session.setAttribute("carrello", carrello);
        return "/paginaFoto";
    }
}
