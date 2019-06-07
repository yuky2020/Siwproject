package siwproject.siwproject.pg;



import org.springframework.data.repository.CrudRepository;

import siwproject.siwproject.model.Amministratore;
public interface AmministratoreRepository extends CrudRepository<Amministratore,Long> {
    Amministratore findByusername(String username);
}