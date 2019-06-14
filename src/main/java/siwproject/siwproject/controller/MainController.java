package siwproject.siwproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
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

import siwproject.siwproject.model.Dettagli;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Richiesta;
import siwproject.siwproject.pg.AlbumService;
import siwproject.siwproject.pg.DettagliService;
import siwproject.siwproject.pg.FotoService;
import siwproject.siwproject.pg.FotografoService;
import siwproject.siwproject.pg.RichiestaService;
import siwproject.siwproject.validator.DettagliValidator;

@Controller
public class MainController {
    @Autowired
    FotografoService fotografoService;
    @Autowired
    AlbumService albumService;
    @Autowired
    FotoService fotoService;
    @Autowired
    DettagliValidator dettagliValidator;
    @Autowired
    RichiestaService richiestaService;
    @Autowired
    DettagliService dettagliService;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        List<Foto> carrello = (List<Foto>) session.getAttribute("carrello");
        if (carrello == null) {
            session.setAttribute("carrello", carrello);
        }
        return "home";
    }

    @RequestMapping(value = "/test")
    public String test(Model model) {
        return "test";
    }

    @GetMapping("/paginaAdmin")
    public String adminpage(Model model) {
        return "paginaAdmin";

    }

    @GetMapping("/cerca")
    public String ciao() {
        return "search";
    }

    @GetMapping("/mostra")
    public String mostra(Model model, HttpSession session) {
        List<Foto> carrello = (List<Foto>) session.getAttribute("carrello");
        model.addAttribute("carrello", carrello);
        return "mostraCarrello";
    }

}
