package eu.virac.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import eu.virac.model.ProjectInformation;
import eu.virac.repo.IProjectInformationRepo;
import eu.virac.service.IProjectService;


@Service
public class ProjectInformationServiceImpl implements IProjectService {

	private IProjectInformationRepo prInfoRepo;
	
	@Override
	public ArrayList<ProjectInformation> selectAllProjects() throws Exception {
		if (prInfoRepo.count() == 0) {
			throw new Exception("Darbinieku tabula ir tukša");
		}
		return (ArrayList<ProjectInformation>) prInfoRepo.findAll();
	}
	
	@Override
	public ProjectInformation selectProjectById(long id) throws Exception {
		if (prInfoRepo.count() == 0) {
			throw new Exception("Studentu tabula ir tukša un nevar atgriezt id");
		}

		if (id < 1) {
			throw new Exception("Id nevar būt negatīvs vai 0");
		}

		if (!prInfoRepo.existsById(id)) {
			throw new Exception("Students ar id" + id + "neeksiste");
		}

		return prInfoRepo.findById(id).get();
	}
	
	@Override
	public void deleteProjectById(int id) throws Exception {
		ProjectInformation projectForDeleting = selectProjectById(id);
		prInfoRepo.delete(projectForDeleting);
	}
	
	@Override	
	public ProjectInformation insertNewProject(ProjectInformation newProject) throws Exception {
		if(newProject == null) {
			throw new Exception("Darbinieka dati nav pieejami, jo nav reference");
		}
		
		if (prInfoRepo.existsByProjectName(newProject.getProjectName())) {
		    throw new Exception("Darbinieks ar tādu persona kodu jau eksistē");
		}
		
		if(newProject.getProjectName() == null || newProject.getProjectNumber() == null
				|| newProject.getStartingDate() == null|| newProject.getEndingDate() == null) {
			throw new Exception("Nav korekti ievades dati");
		}

		return prInfoRepo.save(newProject);
	}
	
	
	@Override
	public void updateByidpi(long id, String projectNumber, String projectName, LocalDate startingDate, LocalDate endingDate) throws Exception {
	    ProjectInformation projectToUpdate = selectProjectById(id);

		if(projectNumber == null  || projectName == null || startingDate == null
				|| endingDate == null) {
			throw new Exception("Nav korekti ievades dati");
		}
	    
	    if (projectNumber != null ) {
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
