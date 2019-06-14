package siwproject.siwproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Richiesta;

public interface RichiestaRepository extends CrudRepository<Richiesta, Long> {
    List<Richiesta> findAll();

    Richiesta findById(long id);
}