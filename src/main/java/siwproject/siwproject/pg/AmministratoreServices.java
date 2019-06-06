package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siwproject.siwproject.model.Amministratore;;


@Service
public class AmministratoreServices {
	@Autowired
	private AmministratoreRepository AmministratoreRepository;
	
	public Amministratore getAmministratoreByusername(String username) {
		Amministratore obj = AmministratoreRepository.findByusername( username);
		return obj;
	}	
	public List<Amministratore> getAllAmministratores(){
		List<Amministratore> list = new ArrayList<>();
		AmministratoreRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	public synchronized boolean addAmministratore(Amministratore amministratore){
	        Amministratore amministratore2= AmministratoreRepository.findByusername(amministratore.getUsername()); 	
                if (amministratore2!=null)return false;
                else {
    	        AmministratoreRepository.save(amministratore);
    	        return true;
       }
	}
	public void updateAmministratore(Amministratore amministratore) {
		AmministratoreRepository.save(amministratore);
	}
	public void deleteAmministratore(String amministratoreUsername) {
		AmministratoreRepository.delete(getAmministratoreByusername(amministratoreUsername));
	}
} 