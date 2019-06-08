package siwproject.siwproject.pg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.repository.AlbumRepository;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Transactional
    public Album albumPerId(long id) {
        return albumRepository.findById(id);
    }

    @Transactional
    public List<Album> tutti() {
        return albumRepository.findAll();
    }

    @Transactional
    public void inserisci(Album a) {
        this.albumRepository.save(a);
    }

    @Transactional
    public Album albumPerNome(String nome) {
        return this.albumRepository.findByNome(nome);
    }

}