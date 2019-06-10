package siwproject.siwproject.pg;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siwproject.siwproject.model.Fotografo;
import siwproject.siwproject.repository.FotoRepository;
import siwproject.siwproject.repository.FotografoRepository;

@Service
public class FotografoService {
	@Autowired
	private FotografoRepository fotografoRepository;

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
	public void cancellaFotografo(Fotografo fotografo) {
		fotografoRepository.delete(fotografo);
	}

	/*
	 * @Transactional public List<Fotografo> searchForName(String name) { return
	 * fotografoRepository.find(name); }
	 */
}