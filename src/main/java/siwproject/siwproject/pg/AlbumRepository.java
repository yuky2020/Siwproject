package siwproject.siwproject.pg;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Fotografo;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    Album findById(long id);

    Album findByNome(String nome);

    List<Album> findByFotografo(Fotografo fotografo);

    List<Album> findAll();
}