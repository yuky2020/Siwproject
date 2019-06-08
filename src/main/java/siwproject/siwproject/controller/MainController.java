package siwproject.siwproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("paginaAdmin")
    public String getConsole(Model model) {
        return "paginaAdmin";
    }
   @RequestMapping("gallery")
   public String getGaleria(Model model,HttpServletRequest a){
       
       return "galleria";
   }
}
