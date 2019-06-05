package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siwproject.siwproject.model.Fotografo;


@Service
public class FotografoServices {
	@Autowired
	private FotografoRepository FotografoRepository;
	
	public Fotografo getFotografoById(long fotografoId) {
		Fotografo obj = FotografoRepository.findById(fotografoId);
		return obj;
	}	
	public List<Fotografo> getAllFotografos(){
		List<Fotografo> list = new ArrayList<>();
		FotografoRepository.findAll().forEach(e -> list.add(e));
		return list;
	}
	public synchronized boolean addFotografo(Fotografo Fotografo){
	        List<Fotografo> list = FotografoRepository.findByNome(Fotografo.getNome()); 	
                if (list.size() > 0) {
    	           return false;
                } else {
    	        FotografoRepository.save(Fotografo);
    	        return true;
       }
	}
	public void updateFotografo(Fotografo Fotografo) {
		FotografoRepository.save(Fotografo);
	}
	public void deleteFotografo(int FotografoId) {
		FotografoRepository.delete(getFotografoById(FotografoId));
	}
} 