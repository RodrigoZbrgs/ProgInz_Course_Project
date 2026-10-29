package eu.virac.repo;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.ProjectContribution;
import eu.virac.model.enums.EmployeePositions;

public interface IProjectContributionRepo extends CrudRepository<ProjectContribution, Long> {

	public abstract ArrayList<ProjectContribution> findByEmployeePosition(EmployeePositions employeePosition);





}
