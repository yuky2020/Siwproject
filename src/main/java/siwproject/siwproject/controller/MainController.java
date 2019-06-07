package siwproject.siwproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import siwproject.siwproject.client.AmazonClient;
import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Amministratore;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.pg.AlbumService;
import siwproject.siwproject.pg.FotoService;
import siwproject.siwproject.pg.FotografoService;

@Controller
public class MainController {
    @Autowired
    FotografoService fotografoService;
    @Autowired
    AlbumService albumService;
    @Autowired
    FotoService fotoService;

    private AmazonClient client;

    @Autowired
    MainController(AmazonClient client) {
        this.client = client;
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        return "home";
    }

    @RequestMapping(value = { "/newFotografo" }, method = RequestMethod.GET)
    public String viewFoto(Model model) {
        return "newFotografo";
    }

    @RequestMapping(value = { "/error" }, method = RequestMethod.GET)
    public String error(Model model) {
        return "404";
    }

    @RequestMapping(value = { "/mostraAlbum" }, method = RequestMethod.GET)
    public String viewAlbum(Model model, HttpServletRequest req) {
        List<Album> albums = albumService.tutti();
        req.setAttribute("albums", albums);

        return "mostraAlbum";
    }

    @RequestMapping(value = { "/mostraFoto" }, method = RequestMethod.GET)
    public String viewFoto(Model model, HttpServletRequest req) {
        List<Foto> fotos = fotoService.tutte();
        req.setAttribute("fotos", fotos);

        return "mostraFoto";
    }

    @RequestMapping(value = { "/mostraFotografi" }, method = RequestMethod.GET)
    public String viewFotografi(Model model, HttpServletRequest req) {
        List<Fotografo> fotografi = fotografoService.tutti();
        req.setAttribute("fotografi", fotografi);

        return "mostraFotografi";
    }

    @RequestMapping(value = { "/paginaFotografo" }, method = RequestMethod.GET)
    public String pageFotografi(Model model) {

        return "paginaFotografo";
    }

    @RequestMapping(value = { "/paginaPrincipale" }, method = RequestMethod.GET)
    public String pagep(Model model) {
        return "ciao";
    }

    @RequestMapping(value = "/test")
    public String test(Model model) {
        return "test";
    }

    @RequestMapping(value = { "/galleria" }, method = RequestMethod.GET)
    public String pagegallery(Model model) {
        List<Foto> foto = fotoService.tutte();
        model.addAttribute("foto", foto);
        return "galleria";
    }

    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String pagelogin(Model model) {

        return "login";

    }

    @RequestMapping(value = { "/fotografoForm" }, method = RequestMethod.GET)
    public String pageFotografoForm(Model model) {

        return "fotografoform";

    }

    @RequestMapping(value = "/SetPassword", method = RequestMethod.GET)
    public String SetPassword(Model model) {
        return "setPassword";
    }

    @RequestMapping(value = "/paginaAdmin", method = RequestMethod.GET)
    public String paginaAdmind(Model model) {
        model.addAttribute("admin", new Amministratore("carlo", "1234"));
        return "paginaAdmin";
    }

}
