package eu.virac.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import eu.virac.model.enums.EmployeePositions;
import eu.virac.repo.IProjectContributionRepo;
import eu.virac.service.IProjectContributionFilter;
import eu.virac.model.ProjectContribution;

@Service
@EnableCaching
public class ProjectContributionFilterImpl implements IProjectContributionFilter {
	
	@Autowired
	private IProjectContributionRepo projConRepo;
	
	@Override
	@Cacheable(value="projectContribution", key="employeePositions")
	public ArrayList<ProjectContribution> filterByPosition(EmployeePositions employeePosition) throws Exception {
		if (projConRepo.count() == 0) {
			throw new Exception("DB has no contributions, so can not be filtered");
		}

		if (employeePosition == null) {
			throw new Exception("Incorrect parameters");
		}

		ArrayList<ProjectContribution> filteredContributions = projConRepo.findByEmployeePosition(employeePosition);

		if (filteredContributions.isEmpty()) {
			throw new Exception("No contribution has position " + employeePosition);
		}
		return filteredContributions;
	}

}
