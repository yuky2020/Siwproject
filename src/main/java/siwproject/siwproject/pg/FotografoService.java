package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import siwproject.siwproject.model.Fotografo;

@Service
public class FotografoService {
	@Autowired
	private FotografoRepository fotografoRepository;

	@Transactional
	public Fotografo inserisci(Fotografo f) {
		return fotografoRepository.save(f);
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
		return !(this.fotografoPerId(f.getId()) == null);
	}

	public Fotografo fotografoPerNome(String nomeFotografo) {
		return this.fotografoRepository.findByNome(nomeFotografo);
	}

}