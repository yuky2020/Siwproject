package siwproject.siwproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Dettagli;

public interface DettagliRepository extends CrudRepository<Dettagli, Long> {
    public List<Dettagli> findAll();
}