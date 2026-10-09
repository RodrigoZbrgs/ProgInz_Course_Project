package eu.virac.service;

import java.util.ArrayList;

import eu.virac.model.Users;

public interface IUserFilter {

	public abstract ArrayList<Users> filterByNameStartingWith(String letter) throws Exception;

	public abstract ArrayList<Users> filterByEmail(String Emailkeyword) throws Exception;

}
