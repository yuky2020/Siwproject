package siwproject.siwproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import siwproject.siwproject.pg.AmministratoreServices;

@Configuration
@EnableWebSecurity
public class BasicConfiguration extends WebSecurityConfigurerAdapter {

   // necessary to prevent security from being applied to the resources
    //such as CSS, images and javascripts
    String [] resources = new String [] {
            "/include/* *", "/css/* *", "/icons/* *", "/img/* *", "/js/* *", "/layer/* *"
    };
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            . authorizeRequests()
	        . antMatchers (resources). permitAll()
	        . antMatchers("/", "/index").permitAll()
	        . antMatchers("/admin *").access("HasRole (' admin ')")
	        . antMatchers ("/user *"). access("HasRole (' user ') or hasRole (' ADMIN ')")
                . anyRequest().authenticated()
                . and ()
            . formLogin()
                . loginPage("/login")
                . permitAll()
                . defaultSuccessUrl("/paginaAdmin")
                . failureForwardUrl("/login? error = True")
                . usernameParameter("username")
                . passwordParameter("password")
                . and ()
            . logout()
                . permitAll()
                . logoutSuccessUrl("/login? Logout");
    }
   
    BCryptPasswordEncoder bCryptPasswordEncoder;
   // Create the password encryption	
    @Bean
    public BCryptPasswordEncoder PasswordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder (4);
//The number 4 represents how strong you want the encryption.
//It can be in a range between 4 and 31. 
//If you do not put a number the program will use one randomly each time
//That you start the application, so your encrypted passwords will not work well
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    AmministratoreServices UserDetailsService;
    //Registers the service for users and the password encryption
    @Autowired
    public void ConfigureGlobal (AuthenticationManagerBuilder auth) throws Exception { 
 
      //  Setting Service to find User in the database.
       // And Setting PassswordEncoder
    auth.userDetailsService(UserDetailsService).passwordEncoder(PasswordEncoder());
    }}