package siwproject.siwproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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



 @GetMapping("/loginValidator")
      String  acessoAdmin(@Valid @RequestParam String username,@RequestParam("pwd")  String pwd ) {

    Amministratore a = amministratoreServices.amministratorePerUsername(username.trim());

        if (a != null && a.checkPwd(pwd.trim()))
            return "paginaAdmin";
        else
          return "login";
        }
    




}
   

    