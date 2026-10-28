package eu.virac.service;

import eu.virac.model.ProjectContribution;
import eu.virac.model.enums.EmployeePositions;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IProjectContributionService extends ICrudBaseService<ProjectContribution> {

	public abstract ArrayList<ProjectContribution> selectAllContributions() throws Exception;

	public abstract ProjectContribution selectContributionById(long id) throws Exception;
	
	public abstract void deleteContributionById(int id) throws Exception;

	public abstract ProjectContribution insertNewContribution(ProjectContribution newContribution) throws Exception;

	public abstract void updateByIdpc(long id, EmployeePositions employeePosition, LocalDate startingDate, LocalDate endingDate)
			throws Exception;
	
}
