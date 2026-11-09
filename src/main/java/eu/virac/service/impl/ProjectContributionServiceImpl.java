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
import eu.virac.model.ProjectInformation;
import eu.virac.model.Users;
import eu.virac.model.enums.EmployeePositions;
import eu.virac.repo.IProjectContributionRepo;
import eu.virac.repo.IProjectInformationRepo;
import eu.virac.repo.IUsersRepo;
import eu.virac.service.IProjectContributionService;

@Service
@EnableCaching
public class ProjectContributionServiceImpl implements IProjectContributionService {

	@Autowired
	private IProjectContributionRepo projConRepo;

	@Autowired
	private IUsersRepo userRepo;

	@Autowired
	private IProjectInformationRepo projRepo;

	@Override
	@Cacheable("projectContribution")
	public ArrayList<ProjectContribution> selectAll() throws Exception {
		if (projConRepo.count() == 0) {
			throw new Exception("Projekta ieguldījuma tabula ir tukša");
		}
		return (ArrayList<ProjectContribution>) projConRepo.findAll();
	}

	@Override
	@Cacheable(value = "projectContribution", key = "#id")
	public ProjectContribution retrieve(long id) throws Exception {
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
	@CacheEvict(value = "projectContribution", key = "#id")
	public void delete(long id) throws Exception {
		ProjectContribution contributionForDeleting = retrieve(id);
		projConRepo.delete(contributionForDeleting);
	}

	@Override
	public void create(ProjectContribution newObject) throws Exception {

		if (newObject == null) {
			throw new Exception("Dati nav pieejami, jo nav referenci");
		}
		if (newObject.getEmployeePosition() == null || newObject.getStartingDate() == null
				|| newObject.getEndingDate() == null) {
			throw new Exception("Nav korekti ievades dati");
		}

		if (newObject.getStartingDate().isAfter(newObject.getEndingDate())) {
			throw new Exception("Nav pareizi ievadits sakumu datums");
		}

		if (newObject.getEndingDate().isBefore(newObject.getStartingDate())) {
			throw new Exception("Nav pareizi ievadits beigu datums");
		}

		if (newObject.getEndingDate().isAfter(LocalDate.now().plusYears(20))) {
			throw new Exception("Nav iespejams tik talu likt beiga datumu");
		}

		if (newObject.getStartingDate().isAfter(LocalDate.now().plusYears(20))) {
			throw new Exception("Nav iespejams tik talu likt sakuma datumu");
		}

		projConRepo.save(newObject);
	}

	@Override
	@CachePut(value = "projectContribution", key = "#id")
	public void update(long id, EmployeePositions employeePosition, LocalDate startingDate, LocalDate endingDate)
			throws Exception {
		ProjectContribution contributionForUpdating = retrieve(id);

		if (employeePosition == null || startingDate == null || endingDate == null) {
			throw new Exception("Nav korekti ievades dati");
		}

		if (endingDate.isAfter(LocalDate.now().plusYears(20))) {
			throw new Exception("Nav iespejams tik talu likt beiga datumu");
		}

		if (startingDate.isAfter(LocalDate.now().plusYears(20))) {
			throw new Exception("Nav iespejams tik talu likt sakuma datumu");
		}

		if (startingDate.isAfter(endingDate)) {
			throw new Exception("Datums nav pareizi ievadits");
		}

		if (endingDate.isBefore(startingDate)) {
			throw new Exception("Datums nav pareizi ievadits");
		}

		if (employeePosition != null) {
			contributionForUpdating.setEmployeePosition(employeePosition);
		}

		if (startingDate != null) {
			contributionForUpdating.setStartingDate(startingDate);
		}
		if (endingDate != null) {
			contributionForUpdating.setEndingDate(endingDate);
		}

		projConRepo.save(contributionForUpdating);
	}

}
