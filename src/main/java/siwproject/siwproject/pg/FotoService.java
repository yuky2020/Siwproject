package siwproject.siwproject.pg;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;

@Service
public class FotoService {
    @Autowired
    private FotoRepository rep;

    
    public Foto getFotoById(long id) {
        Foto foto = rep.findById(id);
        return foto;
    }

    
    public List<Foto> getAllFoto() {
        List<Foto> list = new ArrayList<>();
        rep.findAll().forEach(e -> list.add(e));
        return list;
    }
}