package eu.virac.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import eu.virac.model.Department;
import eu.virac.repo.IDepartmentRepo;
import eu.virac.service.IDepartmentService;

@Service
@EnableCaching
public class DepartmentServiceImpl implements IDepartmentService {
	
	
	@Autowired
	private IDepartmentRepo depRepo;
	
	@Override
	@Cacheable("Departments")
	public ArrayList<Department> selectAllDepartments() throws Exception {
		if(depRepo.count()==0) {
			throw new Exception("Deparment tabula ir tukša");
		}
		return (ArrayList<Department>) depRepo.findAll();
	}
	
	
}
