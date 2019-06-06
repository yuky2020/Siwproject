import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import siwproject.siwproject.model.Amministratore;
import siwproject.siwproject.pg.AmministratoreServices;

@Controller
public class LoginController {
    @Autowired
    AmministratoreServices amministratoreServices;

    @PostMapping("/loginValidator")
	public String acessoAdmin(@RequestBody String username,@RequestBody String password, HttpServletRequest req) {
            Amministratore a = amministratoreServices.getAmministratoreByusername(username);
                
                
            if(a!=null &&a.checkPwd(password)) return "paginaAdmin";
            else return "login";}}