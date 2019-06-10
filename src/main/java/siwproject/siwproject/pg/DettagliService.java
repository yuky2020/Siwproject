package siwproject.siwproject.pg;

import org.springframework.beans.factory.annotation.Autowired;

import siwproject.siwproject.model.Dettagli;
import siwproject.siwproject.repository.DettagliRepository;

public class DettagliService {
    @Autowired
    private DettagliRepository dettagliRepository;

    public void inserisci(Dettagli dettagli) {
        dettagliRepository.save(dettagli);
    }

}