package siwproject.siwproject.pg;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;

public interface FotoRepository extends CrudRepository<Foto, Long> {
    Foto findById(long id);

    Foto findByFotografo(Fotografo fotografo);

    List<Foto> findAll();
}