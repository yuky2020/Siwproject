package siwproject.siwproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import siwproject.siwproject.model.Fotografo;

public interface FotografoRepository extends CrudRepository<Fotografo, Long> {
    Fotografo findById(long Id);

    Fotografo findByNome(String nome);

    boolean existsByNome(String nome);

    List<Fotografo> findAll();

    // @Query("SELECT f FROM Fotografo f WHERE LOWER(f.nome) = LOWER(:nome)")
    // public List<Fotografo> find(@Param("lastName") String nome);
}