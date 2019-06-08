package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siwproject.siwproject.model.Amministratore;
import siwproject.siwproject.repository.AmministratoreRepository;

@Service
public class AmministratoreServices {
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
		return amministratoreRepository.findByusername(username);
	}
}