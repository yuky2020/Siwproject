package siwproject.siwproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Amministratore;

public interface AmministratoreRepository extends CrudRepository<Amministratore, Long> {
    Amministratore findByUsername(String username);

    boolean existsByUsername(String username);

    List<Amministratore> findAll();
}