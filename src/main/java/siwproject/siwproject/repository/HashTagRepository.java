package siwproject.siwproject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.HashTag;

public interface HashTagRepository extends CrudRepository<HashTag, Long> {
    public HashTag findByNome(String nome);

    public List<HashTag> findAll();

    public boolean existsByNome(String nome);
}