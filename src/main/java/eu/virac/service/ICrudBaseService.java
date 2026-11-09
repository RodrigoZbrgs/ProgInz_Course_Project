package eu.virac.service;

import java.util.ArrayList;

public interface ICrudBaseService<Ttype> {

	public abstract void create(Ttype newObject) throws Exception;

	public abstract Ttype retrieve(long id) throws Exception;

	public abstract void delete(long id) throws Exception;
	
	public abstract ArrayList<Ttype> selectAll() throws Exception;



}
