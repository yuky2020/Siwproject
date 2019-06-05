package siwproject.siwproject.pg;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Fotografo;

public interface FotografoRepository extends CrudRepository<Fotografo,Long> {
    Fotografo findById(long Id);

	List<Fotografo> findByNome(String nome);
}