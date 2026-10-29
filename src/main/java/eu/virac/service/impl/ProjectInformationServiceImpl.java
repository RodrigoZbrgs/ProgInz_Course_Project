package eu.virac.service.impl;

import org.springframework.stereotype.Service;

import eu.virac.service.IProjectService;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import eu.virac.model.ProjectInformation;
import eu.virac.repo.IProjectInformationRepo;
import eu.virac.service.IProjectService;

@Service
@EnableCaching
public class ProjectInformationServiceImpl implements IProjectService {

	@Autowired
	private IProjectInformationRepo prInfoRepo;

	@Override
	@Cacheable("projects")
	public ArrayList<ProjectInformation> selectAllProjects() throws Exception {
		if (prInfoRepo.count() == 0) {
			throw new Exception("Projektu tabula ir tukša");
		}
		return (ArrayList<ProjectInformation>) prInfoRepo.findAll();
	}

	@Override
	@Cacheable(value="projects", key="#id") // bez # cache doma ka tas ir ka parasta string funkcija!!!!!
	public ProjectInformation selectProjectById(long id) throws Exception {
		if (prInfoRepo.count() == 0) {
			throw new Exception("Projekta tabula ir tukša un nevar atgriezt id");
		}

		if (id < 1) {
			throw new Exception("Id nevar būt negatīvs vai 0");
		}

		if (!prInfoRepo.existsById(id)) {
			throw new Exception("Projekts ar id" + id + "neeksiste");
		}

		return prInfoRepo.findById(id).get();
	}

	@Override
	@CacheEvict(value = "projects", key = "#id")
	public void deleteProjectById(int id) throws Exception {
		ProjectInformation projectForDeleting = selectProjectById(id);
		prInfoRepo.delete(projectForDeleting);
	}

	@Override
	public ProjectInformation insertNewProject(ProjectInformation newProject) throws Exception {

		if (newProject == null) {
			throw new Exception("Projekta dati nav pieejami, jo nav reference");
		}

		if (prInfoRepo.existsByProjectName(newProject.getProjectName())) {
			throw new Exception("Projekts ar tādu vārdu jau eksistē");
		}

		if (newProject.getProjectName() == null || newProject.getProjectNumber() == null
				|| newProject.getStartingDate() == null || newProject.getEndingDate() == null) {
			throw new Exception("Nav korekti ievades dati");
		}
		
		if(newProject.getStartingDate().isAfter(newProject.getEndingDate())) {
			throw new Exception("Nav pareizi ievadits sakumu datums");
		}
		
		if(newProject.getEndingDate().isBefore(newProject.getStartingDate())) {
			throw new Exception("Nav pareizi ievadits beigu datums");
		}
		
		if(newProject.getEndingDate().isAfter(LocalDate.now().plusYears(20))) {
			throw new Exception("Nav iespejams tik talu likt beiga datumu");
		}

		if(newProject.getStartingDate().isAfter(LocalDate.now().plusYears(20))) {
			throw new Exception("Nav iespejams tik talu likt sakuma datumu");
		}
		
		return prInfoRepo.save(newProject);
	}

	@Override
	@CachePut(value = "projects", key = "#id")
	public void updateByidpi(long id, String projectNumber, String projectName, LocalDate startingDate,
			LocalDate endingDate) throws Exception {
		ProjectInformation projectToUpdate = selectProjectById(id);

		if (projectNumber == null || projectName == null || startingDate == null || endingDate == null) {
			throw new Exception("Nav korekti ievades dati");
		}
		
		if(startingDate.isAfter(endingDate) ) {
			throw new Exception("Datums nav pareizi ievadits");
		}
		
		if(endingDate.isBefore(startingDate)) {
			throw new Exception("Datums nav pareizi ievadits");
		}

		if (projectNumber != null) {
			projectToUpdate.setProjectNumber(projectNumber);
		}
		if (projectName != null) {
			projectToUpdate.setProjectName(projectName);
		}
		if (startingDate != null) {
			projectToUpdate.setStartingDate(startingDate);
		}
		if (endingDate != null) {
			projectToUpdate.setEndingDate(endingDate);
		}

		
		prInfoRepo.save(projectToUpdate);
	}

}
