package siwproject.siwproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        return "home";
    }

    /*
     * 
     * 
     * 
     * QUA SOTTO SONO TEST
     * 
     * 
     * 
     */

    @RequestMapping(value = "/test")
    public String test(Model model) {
        return "test";
    }

}
