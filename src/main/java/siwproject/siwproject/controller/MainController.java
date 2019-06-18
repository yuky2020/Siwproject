package siwproject.siwproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import siwproject.siwproject.model.Foto;
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
        List<Foto> foto = fotoService.ultime30();
        model.addAttribute("fotos", foto);
        return "mostraFoto";
    }

    @GetMapping("/paginaAdmin")
    public String adminpage(Model model) {
        return "paginaAdmin";

    }

    @GetMapping("/cerca")
    public String ciao() {
        return "search";
    }

}
