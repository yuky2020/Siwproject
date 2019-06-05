package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Foto;

@Service
public class FotoService {
    @Autowired
    private FotoRepository fotoRepository;

    @Transactional
    public Foto fotoPerId(long id) {
        return fotoRepository.findById(id);
    }

    @Transactional
    public List<Foto> tutte() {
        return fotoRepository.findAll();
    }

    @Transactional
    public void inserisci(Foto f) {
        this.fotoRepository.save(f);
    }
}