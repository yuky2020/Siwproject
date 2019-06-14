package siwproject.siwproject.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import siwproject.siwproject.client.AmazonClient;
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

    @RequestMapping("/aggiungiFoto")
    public String aggiungiFoto(Model model) {
        Foto foto = new Foto();
        model.addAttribute("foto", foto);
        return "newFoto";
    }

    @RequestMapping(value = "/foto", method = RequestMethod.POST)
    public String inserisciFoto(@Valid @ModelAttribute("foto") Foto foto, Model model, BindingResult bindingResults,
            @RequestPart(value = "file") MultipartFile file, @RequestParam("nomeFotografo") String nomeFotografo,
            @RequestParam("nomeAlbum") String nomeAlbum, @RequestParam("hashTagString") String hashTagString) {
        String url = "https://s3.eu-west-3" + this.amazonClient.uploadFile(file).substring(20);
        foto.setUrl(url);
        Fotografo fotografo = fotografoService.fotografoPerNome(nomeFotografo);
        foto.setFotografo(fotografo);
        Album album = albumService.albumPerNome(nomeAlbum);
        foto.setAlbum(album);
        fotoValidator.validate(foto, bindingResults);

        if (!bindingResults.hasErrors()) {
            List<HashTag> hashTagList = parseHashTag(hashTagString);
            linkTags(foto, hashTagList);
            fotoService.inserisci(foto);
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

    private List<HashTag> parseHashTag(String hashTagString) {
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

    private void linkTags(Foto foto, List<HashTag> HashTags) {
        for (HashTag hashTag : HashTags) {
            hashTag.addFoto(foto);
        }
    }
}
