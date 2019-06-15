package siwproject.siwproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import siwproject.siwproject.model.Fotografo;

public interface FotografoRepository extends CrudRepository<Fotografo, Long> {
    Fotografo findById(long Id);

    Fotografo findByNome(String nome);

    boolean existsByNome(String nome);

    List<Fotografo> findAll();

    @Query(value = "SELECT f.nome FROM Fotografo f", nativeQuery = true)
    public List<String> nomiFotografi();

    @Query(value = "SELECT * FROM fotografo  WHERE LOWER(nome) LIKE CONCAT(:search,'%')", nativeQuery = true)
    public List<Fotografo> fotografiPerNome(@Param("search") String search);

    @Query(value = "SELECT f.nome FROM fotografo f WHERE f.id = :id ", nativeQuery = true)
    public String nomePerId(@Param("id") long id);

    @Modifying
    @Query(value = "update fotografo f set nome = :nome where f.id = :id", nativeQuery = true)
    public void aggiorna(@Param("id") long id, @Param("nome") String nome);
}