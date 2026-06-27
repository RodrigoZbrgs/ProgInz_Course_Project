package eu.virac.repo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.Users;

public interface IUsersRepo extends CrudRepository<Users, Long> {

	boolean existsByUid(int uid);

	Users findByUid(int uid);

	ArrayList<Users> findByNameStartingWith(String letter);

	ArrayList<Users> findByEmail(String email);

}
