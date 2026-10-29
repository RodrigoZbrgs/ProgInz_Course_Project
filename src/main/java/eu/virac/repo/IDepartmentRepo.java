package eu.virac.repo;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.Department;

public interface IDepartmentRepo extends CrudRepository<Department, Long> {

}
