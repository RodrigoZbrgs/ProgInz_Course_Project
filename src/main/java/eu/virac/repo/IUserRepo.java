package eu.virac.repo;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.Users;

public interface IUserRepo extends CrudRepository<Users, Long>{

}
