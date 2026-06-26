package eu.virac.service;

import java.util.ArrayList;

import eu.virac.model.Users;

public interface IUserService extends ICrudBaseService<Users> {

	public abstract ArrayList<Users> selectAllUser() throws Exception;

	public abstract Users selectUsersByUid(int id) throws Exception;

	public abstract void deleteUserByUid(int id) throws Exception;

	public abstract void updateUserByUid(int id, String name, String surname, String email, String password)
	        throws Exception;

}
