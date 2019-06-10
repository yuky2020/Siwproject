package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.amazonaws.services.ecr.model.UploadNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import siwproject.siwproject.model.Amministratore;
import siwproject.siwproject.repository.AmministratoreRepository;

@Service
public class AmministratoreServices implements UserDetailsService {
	@Autowired
	private AmministratoreRepository amministratoreRepository;

	@Transactional
	public void inserisci(Amministratore amministratore) {
		amministratoreRepository.save(amministratore);
	}

	@Transactional
	public List<Amministratore> tutti() {
		return amministratoreRepository.findAll();
	}

	@Transactional
	public Amministratore amministratorePerUsername(String username) {
		return amministratoreRepository.findByUsername(username);
	}

    @Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		
		//Find the user with the repository and if there is no launching a exception
		Amministratore a=amministratoreRepository.findByUsername(username);
		   
	   //  Map our Authority list with the spring security 
	   
	  
		 //  ROLE_USER, ROLE_ADMIN,..
		   GrantedAuthority grantedAuthority = new SimpleGrantedAuthority (a.getRole());
		   List grantList = new ArrayList ();
		   grantList.add(grantedAuthority); 
	   
		   
	  // Create the UserDetails object that is going to be in session and return it.
	   UserDetails userd = (UserDetails) new User( a.getUsername(), a.getPassword(),grantList);
			return userd;
	   }
   }




