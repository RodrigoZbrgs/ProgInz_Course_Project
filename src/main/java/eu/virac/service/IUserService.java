package eu.virac.service;

import java.util.ArrayList;

import eu.virac.model.Users;

public interface IUserService extends ICrudBaseService<Users> {

	public abstract ArrayList<Users> selectAllUser() throws Exception;

	public abstract Users selectUsersById(int id) throws Exception;

	public abstract void deleteUserById(int id) throws Exception;

	public abstract void updateUserById(int id, String name, String surname, String email, String password)
	        throws Exception;

}
