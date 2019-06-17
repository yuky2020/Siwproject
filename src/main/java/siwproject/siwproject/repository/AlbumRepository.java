package siwproject.siwproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import siwproject.siwproject.model.Album;
import siwproject.siwproject.model.Fotografo;

public interface AlbumRepository extends CrudRepository<Album, Long> {
    Album findById(long id);

    List<Album> findByNome(String nome);

    public Album findByNomeAndFotografo(String nome, Fotografo fotografo);

    List<Album> findByFotografo(Fotografo fotografo);

    List<Album> findAll();

    boolean existsByNome(String nome);

    @Query(value = "SELECT a.nome FROM album a where a.fotografo_id = :id ORDER BY a.nome ASC", nativeQuery = true)
    public List<String> nomiAlbum(@Param("id") long fotografoId);

    @Query(value = "SELECT * FROM album a WHERE a.nome LIKE CONCAT('%',:search,'%'", nativeQuery = true)
    public List<Album> albumsPerNome(@Param("search") String toSearch);

}