package siwproject.siwproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Fotografo;

public interface FotografoRepository extends CrudRepository<Fotografo, Long> {
    Fotografo findById(long Id);

    Fotografo findByNome(String nome);

    boolean existsByNome(String nome);

    List<Fotografo> findAll();
}