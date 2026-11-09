package eu.virac.service;

import eu.virac.model.ProjectContribution;
import eu.virac.model.enums.EmployeePositions;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IProjectContributionService extends ICrudBaseService<ProjectContribution> {

	public abstract void update(long id, EmployeePositions employeePosition, LocalDate startingDate, LocalDate endingDate)
			throws Exception;
	
}
