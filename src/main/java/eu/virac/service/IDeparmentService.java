package eu.virac.service;

import java.util.ArrayList;

import eu.virac.model.Department;

public interface IDeparmentService {
	
	public abstract ArrayList<Department> selectAllDeparments() throws Exception;
}
