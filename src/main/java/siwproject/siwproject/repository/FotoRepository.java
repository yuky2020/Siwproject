package siwproject.siwproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Foto;
import siwproject.siwproject.model.Fotografo;

public interface FotoRepository extends CrudRepository<Foto, Long> {
    Foto findById(long id);

    Foto findByFotografo(Fotografo fotografo);

    List<Foto> findAll();

    @Query(value = "SELECT * FROM foto f order by f.creazione DESC limit 30 ", nativeQuery = true)
    List<Foto> last30Foto();
}