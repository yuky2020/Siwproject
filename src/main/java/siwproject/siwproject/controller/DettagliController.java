package siwproject.siwproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import siwproject.siwproject.model.Dettagli;

@Controller
public class DettagliController {
    @PostMapping(value = "/aggiungiDettagli")
    public String creaDettagli(Model model) {
        model.addAttribute("dettagli", new Dettagli());
        return "newDettagli";
    }

}