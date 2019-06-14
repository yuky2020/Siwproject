package siwproject.siwproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import siwproject.siwproject.model.Amministratore;
import siwproject.siwproject.repository.AmministratoreRepository;
import siwproject.siwproject.security.*;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AmministratoreRepository amministratoreRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Amministratore user = amministratoreRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with " + username + "as username");
        }
        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUser(user);
        return userDetails;
    }

}