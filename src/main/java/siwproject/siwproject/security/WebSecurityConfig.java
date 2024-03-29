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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable().and().authorizeRequests()
                .antMatchers("/", "/index", "/fotografi/**", "/photos/**", "/albums/**", "/cerca", "/search",
                        "/carrello/**", "/error/**")
                .permitAll().antMatchers("/resources/**").permitAll().antMatchers("/h2-console/**").permitAll()
                .antMatchers("/Amministratore/createTestAmministratore").permitAll().antMatchers("/").permitAll()
                .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
                .defaultSuccessUrl("/paginaAdmin").and().logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encodePassword());
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

}