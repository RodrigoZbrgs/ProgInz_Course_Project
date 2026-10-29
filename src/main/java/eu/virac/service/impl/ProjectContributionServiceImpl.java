package eu.virac.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import eu.virac.model.ProjectContribution;
import eu.virac.model.enums.EmployeePositions;
import eu.virac.repo.IProjectContributionRepo;
import eu.virac.service.IProjectContributionService;

@Service
@EnableCaching
public class ProjectContributionServiceImpl implements IProjectContributionService {

	@Autowired
	private IProjectContributionRepo projConRepo;

	
	@Override
	@Cacheable("projectContribution")
	public ArrayList<ProjectContribution> selectAllContributions() throws Exception {
		if (projConRepo.count() == 0) {
			throw new Exception("Projekta ieguldījuma tabula ir tukša");
		}
		return (ArrayList<ProjectContribution>) projConRepo.findAll();
	}
	
	@Override
	@Cacheable(value="projectContribution", key="#id")
	public ProjectContribution selectContributionById(long id) throws Exception {
		if (projConRepo.count() == 0) {
			throw new Exception("Projekta ieguldījuma tabula ir tukša un nevar atgriezt id");
		}

		if (id < 1) {
			throw new Exception("Id nevar būt negatīvs vai 0");
		}

		if (!projConRepo.existsById(id)) {
			throw new Exception("Projekta Ieguldījums ar id" + id + "neeksiste");
		}

		return projConRepo.findById(id).get();
	}

	@Override
	@CacheEvict(value="projectContribution", key="#id")
	public void deleteContributionById(int id) throws Exception {
		ProjectContribution contributionForDeleting = selectContributionById(id);
		projConRepo.delete(contributionForDeleting);
	}
	
	@Override
	public ProjectContribution insertNewContribution(ProjectContribution newContribution) throws Exception {

		if(newContribution == null) {
			throw new Exception("Dati nav pieejami, jo nav referenci");
		}
		if (newContribution.getEmployeePosition() == null || newContribution.getStartingDate() == null 
				|| newContribution.getEndingDate() == null) {
			throw new Exception("Nav korekti ievades dati");
		}
		
		if(newContribution.getStartingDate().isAfter(newContribution.getEndingDate())) {
			throw new Exception("Nav pareizi ievadits sakumu datums");
		}
		
		if(newContribution.getEndingDate().isBefore(newContribution.getStartingDate())) {
			throw new Exception("Nav pareizi ievadits beigu datums");
		}
		
		if(newContribution.getEndingDate().isAfter(LocalDate.now().plusYears(20))) {
			throw new Exception("Nav iespejams tik talu likt beiga datumu");
		}

		if(newContribution.getStartingDate().isAfter(LocalDate.now().plusYears(20))) {
			throw new Exception("Nav iespejams tik talu likt sakuma datumu");
		}
		
		return projConRepo.save(newContribution);
	}
	
	@Override
	@CachePut(value="projectContribution", key="#id")
	public void updateByIdpc(long id, EmployeePositions employeePosition, LocalDate startingDate, LocalDate endingDate) throws Exception{
		ProjectContribution contributionForUpdating = selectContributionById(id);
		
		if (employeePosition == null || startingDate == null || endingDate == null) {
			throw new Exception("Nav korekti ievades dati");
		}
		
		if(endingDate.isAfter(LocalDate.now().plusYears(20))) {
			throw new Exception("Nav iespejams tik talu likt beiga datumu");
		}

		if(startingDate.isAfter(LocalDate.now().plusYears(20))) {
			throw new Exception("Nav iespejams tik talu likt sakuma datumu");
		}
		
		if(startingDate.isAfter(endingDate) ) {
			throw new Exception("Datums nav pareizi ievadits");
		}
		
		if(endingDate.isBefore(startingDate)) {
			throw new Exception("Datums nav pareizi ievadits");
		}
		
		if(employeePosition != null) {
			contributionForUpdating.setEmployeePosition(employeePosition);
		}
		
		if(startingDate != null) {
			contributionForUpdating.setStartingDate(startingDate);
		}
		if(endingDate != null) {
			contributionForUpdating.setEndingDate(endingDate);
		}
		
		projConRepo.save(contributionForUpdating);
	}

}
	
	