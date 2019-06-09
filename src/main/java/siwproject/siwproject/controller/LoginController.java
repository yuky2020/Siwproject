package siwproject.siwproject.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import siwproject.siwproject.model.Amministratore;
import siwproject.siwproject.pg.AmministratoreServices;

@Controller
public class LoginController {
    @Autowired
    AmministratoreServices amministratoreServices;
    
    @GetMapping("/login")
    public String paginaLogin(Model model) {
        
        return "login";
    }
    

    @PostMapping("/loginValidator")
    public String acessoAdmin(@RequestBody String username, @RequestBody String password, HttpServletRequest req) {
        
        Amministratore a = amministratoreServices.amministratorePerUsername(username.trim());

        if (a != null && a.checkPwd(password.trim()))
            return "paginaAdmin";
        else
            return "login";
    }
}