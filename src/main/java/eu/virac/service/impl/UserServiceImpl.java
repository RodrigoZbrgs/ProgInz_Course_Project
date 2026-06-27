package eu.virac.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.model.Users;
import eu.virac.repo.IUsersRepo;
import eu.virac.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUsersRepo userRepo;

	@Override
	public ArrayList<Users> selectAllUser() throws Exception {
		if (userRepo.count() == 0) {
			throw new Exception("Projektu tabula ir tukša");
		}
		return (ArrayList<Users>) userRepo.findAll();
	}

	@Override
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
	public void deleteUserByUid(int id) throws Exception {
		Users UserForDeleting = selectUsersByUid(id);
		userRepo.delete(UserForDeleting);

	}

	@Override
	public void updateUserByUid(int id, String name, String surname, String email, String password) throws Exception {
		Users userToUpdate = selectUsersByUid(id);
		if (name == null || surname == null || email == null || password == null) {
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
		userRepo.save(userToUpdate);
	}

}
