package siwproject.siwproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.pg.AlbumService;
import siwproject.siwproject.pg.FotoService;
import siwproject.siwproject.pg.FotografoServices;

@Controller
public class MainController {
    @Autowired
    FotografoServices fotografoServices;
    @Autowired
    AlbumService albumServices;
    @Autowired
    FotoService fotoServices;

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
        List<Album> albums = albumServices.getAlbum();
        req.setAttribute("albums", albums);

        return "mostraAlbum";
    }

    @RequestMapping(value = { "/mostraFoto" }, method = RequestMethod.GET)
    public String viewFoto(Model model, HttpServletRequest req) {
        List<Foto> fotos = fotoServices.getAllFoto();
        req.setAttribute("fotos", fotos);

        return "mostraFoto";
    }

    @RequestMapping(value = { "/mostraFotografi" }, method = RequestMethod.GET)
    public String viewFotografi(Model model, HttpServletRequest req) {
        List<Fotografo> fotografi = fotografoServices.getAllFotografos();
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

    @RequestMapping(value = { "/gallery" }, method = RequestMethod.GET)
    public String pagegallery(Model model) {

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
    @RequestMapping(value="/SetPassword", method=RequestMethod.GET)
    public String SetPassword(Model model) {
        return "setPassword";
    }
    

}
