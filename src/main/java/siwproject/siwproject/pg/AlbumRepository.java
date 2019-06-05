package siwproject.siwproject.pg;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Album;

public interface AlbumRepository extends CrudRepository<Album,Long>{
    Album findById (long id);
    List<Album> findByNome (String nome);
}