package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.repository.FotoRepository;

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
        return this.fotoRepository.findAll();
    }

    @Transactional
    public void inserisci(Foto f) {
        f.getFotografo().addFoto(f);
        f.getAlbum().addFoto(f);
        this.fotoRepository.save(f);
    }

    @Transactional
    public void aggiorna(Foto foto) {

    }
}