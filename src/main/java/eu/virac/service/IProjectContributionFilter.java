package eu.virac.service;

import java.util.ArrayList;

import eu.virac.model.ProjectContribution;
import eu.virac.model.enums.EmployeePositions;

public interface IProjectContributionFilter {
	
	public ArrayList<ProjectContribution> filterByPosition(EmployeePositions employeePosition) throws Exception;
}
