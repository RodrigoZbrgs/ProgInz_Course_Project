package eu.virac.repo;

import java.util.ArrayList;


import org.springframework.data.repository.CrudRepository;

import eu.virac.model.Users;

public interface IUsersRepo extends CrudRepository<Users, Long> {

	boolean existsByUid(long uid);

	Users findByUid(long uid);

	ArrayList<Users> findByNameStartingWith(String letter);

	ArrayList<Users> findByEmail(String email);

	Users findByUsername(String username);

	boolean existsByUsername(String username);

}
