package siwproject.siwproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import siwproject.siwproject.model.Amministratore;
import siwproject.siwproject.model.Role;
import siwproject.siwproject.repository.AmministratoreRepository;
import siwproject.siwproject.security.CustomUserDetailsService;;

@Controller
public class LoginController {

  @Autowired
  private AmministratoreRepository amministratoreRepository;

  @Autowired
  private BCryptPasswordEncoder pwdEncoder;

  @Autowired
  CustomUserDetailsService amministratoreServices;

  @GetMapping("/login")
  public String paginaLogin(Model model) {
    return "login";
  }

  @GetMapping("/Amministratore/createTestAmministratore")
  public String createTestUser() {
    Role role = new Role();
    role.setRole("OFFICIAL");
    Amministratore user = new Amministratore();
    user.setUsername("matteo");
    user.addRole(role);
    String encryptedPwd = pwdEncoder.encode("bianchi");
    user.setPassword(encryptedPwd);
    amministratoreRepository.save(user);

    return "login";
  }

  @GetMapping("/setPassword")
  public String SetPassword() {
    return "setPassword";
  }

}
