package eu.virac.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import eu.virac.model.Users;
import eu.virac.repo.IUsersRepo;
import eu.virac.service.IUserFilter;

@Service
@EnableCaching
public class UserFilterServiceImpl implements IUserFilter {
	@Autowired
	private IUsersRepo userRepo;

	@Override
	@Cacheable(value="users", key="#letter")
	public ArrayList<Users> filterByStartingWith(String letter) throws Exception {
		if (letter == null || letter.isEmpty()) {
			throw new Exception("Letter cannot be empty");
		}
		return userRepo.findByNameStartingWith(letter);
	}

	@Override
	@Cacheable(value="users", key="#Emailkeyword")
	public ArrayList<Users> filterByEmail(String Emailkeyword) throws Exception {
		if (Emailkeyword == null || Emailkeyword.isEmpty()) {
			throw new Exception("Email keyword cannot be empty");
		}
		return userRepo.findByEmail(Emailkeyword);

	}

}
