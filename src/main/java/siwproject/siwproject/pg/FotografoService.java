package siwproject.siwproject.pg;

import java.util.List;

import javax.persistence.Lob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.repository.AlbumRepository;
import siwproject.siwproject.repository.FotoRepository;
import siwproject.siwproject.repository.FotografoRepository;

@Service
public class FotografoService {
	@Autowired
	private AlbumService albumService;
	@Autowired
	private FotografoRepository fotografoRepository;
	@Autowired
	private FotoRepository fotoRepository;
	@Autowired
	private AlbumRepository albumRepository;

	@Transactional
	public void inserisci(Fotografo f) {
		this.fotografoRepository.save(f);
	}

	@Transactional
	public Fotografo fotografoPerId(long id) {
		return fotografoRepository.findById(id);
	}

	@Transactional
	public List<Fotografo> tutti() {
		return this.fotografoRepository.findAll();
	}

	@Transactional
	public boolean alreadyExists(Fotografo f) {
		return fotografoRepository.existsByNome(f.getNome());
	}

	@Transactional
	public Fotografo fotografoPerNome(String nomeFotografo) {
		return this.fotografoRepository.findByNome(nomeFotografo);
	}

	@Transactional
	public void cancellaFotografo(long id) {
		Fotografo fotografo = fotografoRepository.findById(id);
		List<Album> albums=fotografo.getAlbum();
		for(Album dalbum:albums) albumService.cancellaAlbum(dalbum.getId());

		fotografoRepository.delete(fotografo);
	}

	@Transactional
	public List<String> nomiFotografi() {
		return fotografoRepository.nomiFotografi();
	}

	@Transactional
	public List<Fotografo> fotografiPerNome(String search) {
		return fotografoRepository.fotografiPerNome(search);
	}

	@Transactional
	public String nomePerId(long id) {
		return fotografoRepository.nomePerId(id);
	}


		


		

	
}