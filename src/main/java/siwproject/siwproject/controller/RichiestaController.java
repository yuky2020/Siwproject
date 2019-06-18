package siwproject.siwproject.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import siwproject.siwproject.model.Dettagli;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Richiesta;
import siwproject.siwproject.pg.DettagliService;
import siwproject.siwproject.pg.FotoService;
import siwproject.siwproject.pg.RichiestaService;
import siwproject.siwproject.validator.DettagliValidator;

@Controller
public class RichiestaController {
    @Autowired
    DettagliValidator dettagliValidator;
    @Autowired
    DettagliService dettagliService;
    @Autowired
    RichiestaService richiestaService;
    @Autowired
    FotoService fotoService;

    @GetMapping("carrello/aggiungiAlCarrello/{id}")
    public String aggiungiFotoAlCarrello(Model model, HttpSession session, @PathVariable("id") long id,
            HttpServletRequest req) {
        Foto foto = fotoService.fotoPerId(id);
        List<Foto> carrello = (List<Foto>) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new ArrayList<Foto>();
        }
        carrello.add(foto);
        session.setAttribute("carrello", carrello);
        return "redirect:" + req.getHeader("Referer");
    }

    @GetMapping("carrello")
    public String mostra(Model model, HttpSession session) {
        List<Foto> carrello = (List<Foto>) session.getAttribute("carrello");
        if (carrello != null && carrello.size() != 0) {
            model.addAttribute("carrello", carrello);
        }
        return "mostraCarrello";
    }

    @GetMapping("carrello/aggiungiDettagli")
    public String creaDettagli(Model model) {
        model.addAttribute("dettagli", new Dettagli());
        return "newDettagli";
    }

    @PostMapping("carrello/aggiungiDettagli/dettagli")
    public String setDettagli(HttpSession session, Model model, @Valid @ModelAttribute("dettagli") Dettagli dettagli,
            BindingResult errors) {
        dettagliValidator.validate(dettagli, errors);
        if (!errors.hasErrors()) {
            dettagliService.inserisci(dettagli);
            List<Foto> carrello = (List<Foto>) session.getAttribute("carrello");
            Richiesta richiesta = new Richiesta(carrello, dettagli);
            richiestaService.inserisci(richiesta);
            List<Richiesta> richieste = richiestaService.tutte();
            model.addAttribute("richieste", richieste);
            session.setAttribute("carrello", new ArrayList<Foto>());
            return "mostraFoto";
        } else {
            return "newDettagli";
        }
    }

    @GetMapping("/carrello/rimuovi/{id}")
    public String rimuoviDalCarrello(Model model, @PathVariable("id") long id, HttpSession session) {
        List<Foto> carrello = (List<Foto>) session.getAttribute("carrello");
        System.out.println(carrello.remove(fotoService.fotoPerId(id)));
        session.setAttribute("carrello", carrello);
        return "mostraCarrello";
    }

    @GetMapping("/carrello/svuotaCarrello")
    public String svuotaCarrello(Model model, HttpSession session) {
        session.setAttribute("carrello", new ArrayList<Foto>());
        return "mostraCarrello";
    }

    @GetMapping("paginaAdmin/paginaRichiesta/{id}")
    public String paginaRichiesta(Model model, @PathVariable("id") long id) {
        model.addAttribute("richiesta", richiestaService.richiestaPerId(id));
        return "paginaRichiesta";
    }

    @GetMapping("paginaAdmin/listaRichieste")
    public String mostraRichieste(Model model) {
        model.addAttribute("richieste", richiestaService.tutte());
        return "listaRichieste";
    }

    @GetMapping("paginaAdmin/deleteRichiesta/{id}")
    public String rimuoviRichiesta(Model model, @PathVariable("id") long id) {
        richiestaService.elimina(id);
        model.addAttribute("richieste", richiestaService.tutte());
        return "listaRichieste";
    }
}