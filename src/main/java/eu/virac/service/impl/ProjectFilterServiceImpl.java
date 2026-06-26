package eu.virac.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.model.ProjectInformation;
import eu.virac.repo.IProjectInformationRepo;
import eu.virac.service.IProjectFIlter;

@Service
public class ProjectFilterServiceImpl implements IProjectFIlter {

	@Autowired
	private IProjectInformationRepo projRepo;
	
	//Filter by starting date
	public ArrayList<ProjectInformation> filterByStartingDate(LocalDate startingDate) throws Exception {
		if (startingDate == null) {
			throw new Exception("Incorrect parameters");
		}

		if (projRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be filtered");
		}

		ArrayList<ProjectInformation> filteredProjects = projRepo.findByStartingDate(startingDate);

		if (filteredProjects.isEmpty()) {
			throw new Exception("No project has started or starting in " + startingDate);
		}
		return filteredProjects;
	}
	
	//Filter by ending date
	public ArrayList<ProjectInformation> filterByEndingDate(LocalDate endingDate) throws Exception {
		if (endingDate == null) {
			throw new Exception("Incorrect parameters");
		}

		if (projRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be filtered");
		}

		ArrayList<ProjectInformation> filteredProjects = projRepo.findByEndingDate(endingDate);

		if (filteredProjects.isEmpty()) {
			throw new Exception("No project has ended or is ending in " + endingDate);
		}
		return filteredProjects;
	}
	
	//Filter by activity
	public ArrayList<ProjectInformation> filterByActivity(Boolean activity) throws Exception {
		if (activity == null) {
			throw new Exception("Incorrect parameters");
		}

		if (projRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be filtered");
		}

		ArrayList<ProjectInformation> filteredProjects = projRepo.findByIsActive(activity);

		if (filteredProjects.isEmpty()) {
			throw new Exception("No project is in " + activity + "status");
		}
		return filteredProjects;
	}
	//Filter by keyword
	public ArrayList<ProjectInformation> filterByKeyWord(String keyword) throws Exception {
		if (keyword == null) {
			throw new Exception("Incorrect parameters");
		}

		if (projRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be found");
		}

		ArrayList<ProjectInformation> filteredProjects = projRepo.findByProjectNameContaining(keyword);
		if (filteredProjects.isEmpty()) {
			throw new Exception("No projects with " + keyword + " found");
		}
		return filteredProjects;
	}

}
