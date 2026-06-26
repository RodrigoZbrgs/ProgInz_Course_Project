package eu.virac.repo;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.Users;

public interface IUsersRepo extends CrudRepository<Users, Long> {

	boolean existsById(int id);

	Users findById(int id);

	ArrayList<Users> findByNameStartingLetter(String letter);

	ArrayList<Users> findByEmail(String email);



}
