package eu.virac.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import eu.virac.model.ProjectInformation;
import eu.virac.repo.IProjectInformationRepo;
import eu.virac.service.IProjectFIlter;

@Service
@EnableCaching
public class ProjectFilterServiceImpl implements IProjectFIlter {

	@Autowired
	private IProjectInformationRepo projRepo;

	// Filter by starting date
	@Cacheable(value="projects", key="#startingDate")
	public ArrayList<ProjectInformation> filterByStartingDate(LocalDate startingDate) throws Exception {
		if (projRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be filtered");
		}
		
		if (startingDate == null) {
			throw new Exception("Incorrect parameters");
		}

		ArrayList<ProjectInformation> filteredProjects = projRepo.findByStartingDate(startingDate);

		if (filteredProjects.isEmpty()) {
			throw new Exception("No project has started or starting in " + startingDate);
		}
		return filteredProjects;
	}

	// Filter by ending date
	@Cacheable(value="projects", key="#endingDate")
	public ArrayList<ProjectInformation> filterByEndingDate(LocalDate endingDate) throws Exception {
		
		if (projRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be filtered");
		}
		
		if (endingDate == null) {
			throw new Exception("Incorrect parameters");
		}

		ArrayList<ProjectInformation> filteredProjects = projRepo.findByEndingDate(endingDate);

		if (filteredProjects.isEmpty()) {
			throw new Exception("No project has ended or is ending in " + endingDate);
		}
		return filteredProjects;
	}
	
	// Filter by specific date


	// Filter by activity
	@Cacheable(value="projects", key="#active")
	public ArrayList<ProjectInformation> filterByActivity(Boolean activity) throws Exception {
		if (projRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be filtered");
		}
		
		if (activity == null) {
			throw new Exception("Incorrect parameters");
		}

		ArrayList<ProjectInformation> filteredProjects = projRepo.findByActive(activity);

		if (filteredProjects.isEmpty()) {
			throw new Exception("No project is in " + activity + "status");
		}
		return filteredProjects;
	}

	// Filter by keyword
	@Cacheable(value="projects", key="#keyword")
	public ArrayList<ProjectInformation> filterByKeyWord(String keyword) throws Exception {
		if (projRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be found");
		}
		
		if (keyword == null) {
			throw new Exception("Incorrect parameters");
		}

		ArrayList<ProjectInformation> filteredProjects = projRepo.findByProjectNameContaining(keyword);
		if (filteredProjects.isEmpty()) {
			throw new Exception("No projects with " + keyword + " found");
		}
		return filteredProjects;
	}
	
	//Filter by project number
	@Cacheable(value="projects", key="#projectNumber")
	public ArrayList<ProjectInformation> filterByProjectNumber(String projectNumber) throws Exception {
		if (projRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be found");
		}
		
		if (projectNumber == null) {
			throw new Exception("Incorrect parameters");
		}
		
		ArrayList<ProjectInformation> filteredProjects = projRepo.findByProjectNumberContaining(projectNumber);
		if (filteredProjects.isEmpty()) {
			throw new Exception("Project with project number " + projectNumber + " not found");
		}
		return filteredProjects;
	}

	
	//filter by specific date
	@Override
	@Cacheable(value="projects", key="#date")
	public ArrayList<ProjectInformation> filterBySpecificDate(LocalDate date) throws Exception {
		if (projRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be found");
		}
		
		if(date == null) {
			throw new Exception("Invalid or empty date has been entered");
		}
		ArrayList<ProjectInformation> filteredProjects = projRepo.findByStartingDateBeforeOrEndingDateAfter(date, date);
		if (filteredProjects.isEmpty()) {
			throw new Exception("Project with date " + date + " has not been found");
		}
		return filteredProjects;
}

}

