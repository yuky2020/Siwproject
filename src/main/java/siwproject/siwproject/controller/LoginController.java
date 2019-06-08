package siwproject.siwproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import siwproject.siwproject.model.Amministratore;
import siwproject.siwproject.pg.AmministratoreServices;

@Controller
public class LoginController {
    @Autowired
    AmministratoreServices amministratoreServices;

    @PostMapping("/loginValidator")
    public String acessoAdmin(@RequestBody String username, @RequestBody String password, HttpServletRequest req) {
        // solo per il testing
        Amministratore root = new Amministratore("root", "toor");

        amministratoreServices.inserisci(root);
        // fine
        Amministratore a = amministratoreServices.amministratorePerUsername(username);

        if (a != null && a.checkPwd(password))
            return "paginaAdmin";
        else
            return "login";
    }
}