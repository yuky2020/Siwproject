package siwproject.siwproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
     * @RequestMapping(value = "/cerca", method = RequestMethod.GET) public String
     * cerca(Model model, @RequestParam("toSearch") String toSearch) {
     * List<Fotografo> fotografi = fotografoService.searchForName(toSearch);
     * model.addAttribute("results", fotografi); return "results"; }
     */
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

    @GetMapping("/paginaAdmin")
    public String adminpage(Model model) {
        return "paginaAdmin";

    }

}
