package siwproject.siwproject.pg;



import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Foto;
public interface FotoRepository extends CrudRepository<Foto,Long> {
    Foto findById(long id);
}