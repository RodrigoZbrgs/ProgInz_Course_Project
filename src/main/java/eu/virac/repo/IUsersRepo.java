package eu.virac.repo;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.Users;

public interface IUsersRepo extends CrudRepository<Users, Long> {

}
