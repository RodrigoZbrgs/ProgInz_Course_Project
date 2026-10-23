package eu.virac.repo;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.Department;

public interface IDeparmentRepo extends CrudRepository<Department, Long> {

}
