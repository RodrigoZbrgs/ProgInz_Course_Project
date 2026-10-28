package eu.virac.service;

import java.util.ArrayList;

import eu.virac.model.Department;

public interface IDepartmentService {
	
	public abstract ArrayList<Department> selectAllDepartments() throws Exception;
}
