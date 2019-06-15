package siwproject.siwproject.pg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Foto;
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
    public Album albumPerNome(String nome) {
        return this.albumRepository.findByNome(nome);
    }

    @Transactional
    public boolean doesExists(String nomeAlbum) {
        return this.albumRepository.existsByNome(nomeAlbum);
    }

    @Transactional
    public List<String> nomiAlbum(long fotografoId) {
        return albumRepository.nomiAlbum(fotografoId);
    }


    @Transactional
	public void cancellaAlbum(long id) {
		Album album =albumRepository.findById(id);
		List<Foto> foto= album.getFoto();
		for(Foto dfoto:foto) fotoService.cancella(dfoto.getId());
		albumRepository.delete(album);
	}
}