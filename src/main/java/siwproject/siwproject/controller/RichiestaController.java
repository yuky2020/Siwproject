package siwproject.siwproject.controller;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping(value = "/aggiungiDettagli")
    public String creaDettagli(Model model) {
        model.addAttribute("dettagli", new Dettagli());
        return "newDettagli";
    }

    @PostMapping("/dettagli")
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
            return "listaRichieste";
        } else {
            return "newDettagli";
        }
    }

    @GetMapping("/paginaRichiesta/{id}")
    public String paginaRichiesta(Model model, @PathVariable("id") long id) {
        model.addAttribute("richiesta", richiestaService.richiestaPerId(id));
        return "paginaRichiesta";
    }

    @GetMapping("/listaRichieste")
    public String mostraRichieste(Model model) {
        model.addAttribute("richieste", richiestaService.tutte());
        return "listaRichieste";
    }

    @GetMapping("/deleteRichiesta/{id}")
    public String rimuoviRichiesta(Model model, @PathVariable("id") long id) {
        richiestaService.elimina(id);
        model.addAttribute("richieste", richiestaService.tutte());
        return "listaRichieste";
    }
}