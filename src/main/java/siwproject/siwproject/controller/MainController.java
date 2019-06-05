package siwproject.siwproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

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

    @RequestMapping(value = { "/gallery" }, method = RequestMethod.GET)
    public String pagegallery(Model model) {

        return "galleria";
    }
}
