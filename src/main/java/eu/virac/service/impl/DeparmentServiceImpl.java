package eu.virac.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import eu.virac.model.Department;
import eu.virac.repo.IDeparmentRepo;
import eu.virac.service.IDeparmentService;

public class DeparmentServiceImpl implements IDeparmentService {
	
	
	@Autowired
	private IDeparmentRepo depRepo;
	
	@Override
	public ArrayList<Department> selectAllDeparments() throws Exception {
		if(depRepo.count()==0) {
			throw new Exception("Deparment tabula ir tukša");
		}
		return (ArrayList<Department>) depRepo.findAll();
	}
	
	
}
