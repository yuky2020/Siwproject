package siwproject.siwproject.pg;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siwproject.siwproject.model.Richiesta;
import siwproject.siwproject.repository.RichiestaRepository;

@Service
public class RichiestaService {
    @Autowired
    RichiestaRepository richiestaRepository;

    @Transactional
    public List<Richiesta> tutte() {
        return richiestaRepository.findAll();
    }

    @Transactional
    public void inserisci(Richiesta richiesta) {
        richiestaRepository.save(richiesta);
    }

    @Transactional
    public Richiesta richiestaPerId(long id) {
        return richiestaRepository.findById(id);
    }

    @Transactional
    public void elimina(long id) {
        richiestaRepository.delete(richiestaRepository.findById(id));
    }
}