package eu.virac.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import eu.virac.model.Department;
import eu.virac.model.Users;
import eu.virac.repo.IUsersRepo;
import eu.virac.service.IUserService;


@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUsersRepo userRepo;
	
	@Override
	public void create(Users newObject) throws Exception {
		userRepo.save(newObject);
		
	}

	@Override
	public ArrayList<Users> selectAll() throws Exception {
		if (userRepo.count() == 0) {
			throw new Exception("User tabula ir tukša");
		}
		return (ArrayList<Users>) userRepo.findAll();
	}

	@Override
	@Cacheable(value = "users", key = "#id")
	public Users retrieve(long id) throws Exception {
		if (userRepo.count() == 0) {
			throw new Exception("Users tabula ir tukša un nevar atgriezt id");
		}

		if (id < 1) {
			throw new Exception("Id nevar būt negatīvs vai 0");
		}

		if (!userRepo.existsByUid(id)) {
			throw new Exception("User ar id" + id + "neeksiste");
		}

		return userRepo.findByUid(id);
	}

	@Override
	@CacheEvict(value = "users", key = "#id")
	public void delete(long id) throws Exception {
		Users UserForDeleting = retrieve(id);
		userRepo.delete(UserForDeleting);

	}

	@Override
	@CachePut(value = "users", key = "#id")
	public void update(int id, String name, String surname, String email, String password,
			Department department) throws Exception {
		Users userToUpdate = retrieve(id);
		if (name == null || surname == null || email == null || password == null || department == null) {
			throw new Exception("Nav korekti ievades dati");
		}
		if (name != null) {
			userToUpdate.setName(name);
		}
		if (surname != null) {
			userToUpdate.setSurname(surname);
		}
		if (email != null) {
			userToUpdate.setEmail(email);
		}
		if (password != null) {
			userToUpdate.setPassword(password);
		}
		if (department != null) {
			userToUpdate.setDepartment(department);
		}
		userRepo.save(userToUpdate);
	}



}
