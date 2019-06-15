package siwproject.siwproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Fotografo;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    Album findById(long id);

    Album findByNome(String nome);

    List<Album> findByFotografo(Fotografo fotografo);

    List<Album> findAll();

    boolean existsByNome(String nome);

    @Query(value = "SELECT a.nome FROM album a where a.fotografo_id = :id ORDER BY a.nome ASC", nativeQuery = true)
    public List<String> nomiAlbum(@Param("id") long fotografoId);

}