package eu.virac.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import eu.virac.model.Department;
import eu.virac.model.Users;
import eu.virac.repo.IUsersRepo;
import eu.virac.service.IUserService;

@Service
@EnableCaching
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUsersRepo userRepo;

	@Override
	@Cacheable("users")
	public ArrayList<Users> selectAllUser() throws Exception {
		if (userRepo.count() == 0) {
			throw new Exception("User tabula ir tukša");
		}
		return (ArrayList<Users>) userRepo.findAll();
	}

	@Override
	@Cacheable(value="users", key="#id")
	public Users selectUsersByUid(int id) throws Exception {
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
	@CacheEvict(value="users", allEntries = true)
	public void deleteUserByUid(int id) throws Exception {
		Users UserForDeleting = selectUsersByUid(id);
		userRepo.delete(UserForDeleting);

	}

	@Override
	@CachePut(value="users", key="#id")
	public void updateUserByUid(int id, String name, String surname, String email, String password ,Department department) throws Exception {
	    Users userToUpdate = selectUsersByUid(id);
	    if (name == null || surname == null || email == null || password == null || department == null ) {
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
