package eu.virac.service;

import eu.virac.model.Department;
import eu.virac.model.Users;

public interface IUserService extends ICrudBaseService<Users> {

	public abstract void update(int id, String name, String surname, String email, String password, Department department)
			throws Exception;
}
