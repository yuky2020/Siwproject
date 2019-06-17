package siwproject.siwproject.pg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.repository.AlbumRepository;
import siwproject.siwproject.repository.FotoRepository;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private FotoService fotoService;

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
        a.getFotografo().addAlbum(a);
        this.albumRepository.save(a);
    }

    @Transactional
    public List<String> nomiAlbum(long fotografoId) {
        return albumRepository.nomiAlbum(fotografoId);
    }

    @Transactional
    public void cancellaAlbum(long id) {
        Album album = albumRepository.findById(id);
        List<Foto> foto = album.getFoto();
        for (Foto dfoto : foto)
            fotoService.cancella(dfoto.getId());
        albumRepository.delete(album);
    }

    @Transactional
    public Album albumPerNomeAndFotografo(String nome, Fotografo fotografo) {
        return albumRepository.findByNomeAndFotografo(nome, fotografo);
    }

    @Transactional
    public List<Album> albumsPerNome(String nome) {
        return albumRepository.albumsPerNome(nome);
    }
}