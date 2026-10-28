package eu.virac.repo.security;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.MyAuthority;


public interface IMyAuthorityRepo extends CrudRepository<MyAuthority, Long>{

}
