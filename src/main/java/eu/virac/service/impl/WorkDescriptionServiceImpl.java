package eu.virac.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import eu.virac.model.KPIWorkDescription;
import eu.virac.model.Users;
import eu.virac.model.enums.Priority;
import eu.virac.repo.IKPIWorkDescriptionRepo;
import eu.virac.service.IWorkDescriptionService;
import eu.virac.service.IWorkDescriptionsFilter;

@Service
public class WorkDescriptionServiceImpl implements IWorkDescriptionService, IWorkDescriptionsFilter {
	@Autowired
	private IKPIWorkDescriptionRepo workDescriptionRepo;

	@Override
	public ArrayList<KPIWorkDescription> filterByDescription(String keyword) throws Exception {
		if (keyword == null || keyword.isEmpty()) {
			throw new Exception("keyword cannot be empty");
		}
		return workDescriptionRepo.findByDescriptionContainingIgnoreCase(keyword);
	}

	@Override
	public ArrayList<KPIWorkDescription> filterByDate(LocalDate date) throws Exception {
		if (date == null) {
			throw new Exception("Date cannot be empty");
		}
		return workDescriptionRepo.findByDate(date);
	}

//	@Override
//	public ArrayList<KPIWorkDescription> filterByAmountGreaterThan(int amount) throws Exception {
//		if (amount < 1 || amount > 10) {
//			throw new Exception("Amount must be at least 1 and lesser than 10");
//		}
//		return workDescriptionRepo.findByAmountGreaterThan(amount);
//	}

	@Override
	public ArrayList<KPIWorkDescription> findAllWorkDescriptions() throws Exception {
		if (workDescriptionRepo.count() == 0) {
			throw new Exception("Work description amount is empty");
		}
		return (ArrayList<KPIWorkDescription>) workDescriptionRepo.findAll();
	}

	@Override
	public KPIWorkDescription addWorkDescription(KPIWorkDescription workDescription) throws Exception {

		if (workDescription == null) {
			throw new Exception("Work description data is not available");
		}
		if (workDescription.getDescription() == null || workDescription.getDescription().isEmpty()) {
			throw new Exception("Description cannot be empty");
		}
		if (workDescription.getDate() == null) {
			throw new Exception("Date cannot be empty");
		}
		LocalDate date = workDescription.getDate();
		LocalDate today = LocalDate.now();
	    LocalDate minDate = today.minusDays(1);
	    LocalDate maxDate = today.plusYears(5);
	    if (date.isBefore(minDate) || date.isAfter(maxDate)) {
	        throw new Exception("Date must be between " + minDate + " and " + maxDate);
	    }
		if (workDescriptionRepo.existsByDescription(workDescription.getDescription())) {
			throw new Exception("Work description with this name already exists");
		}
		
		return workDescriptionRepo.save(workDescription);

	}

	@Override
	public KPIWorkDescription updateWorkDescription(long id, String description, LocalDate date, Priority priority, Users user)
			throws Exception {
		KPIWorkDescription workDescriptionToUpdate = findWorkDescriptionById(id);

		if (description == null || description.isEmpty() || date == null) {
			throw new Exception("Invalid input data");
		}

		if (description != null) {
			workDescriptionToUpdate.setDescription(description);
		}
		if (date != null) {
			workDescriptionToUpdate.setDate(date);
		}
		if (priority != null) {
			workDescriptionToUpdate.setPriority(priority);
		}
		if (user != null) {
			workDescriptionToUpdate.setUser(user);
		}
	    LocalDate today = LocalDate.now();
	    LocalDate minDate = today.minusYears(5);
	    LocalDate maxDate = today.plusYears(5);
	    if (date.isBefore(minDate) || date.isAfter(maxDate)) {
	        throw new Exception("Date must be between " + minDate + " and " + maxDate);
	    }
		
		return workDescriptionRepo.save(workDescriptionToUpdate);
	}

	@Override
	public void deleteWorkDescription(long id) throws Exception {
		if (id < 1) {
			throw new Exception("Id cannot be negative or 0");
		}
		if (!workDescriptionRepo.existsById(id)) {
			throw new Exception("Work description with id " + id + " does not exist");
		}
		workDescriptionRepo.deleteById(id);
	}

	@Override
	public KPIWorkDescription findWorkDescriptionById(long id) throws Exception {

		if (!workDescriptionRepo.existsById(id)) {
			throw new Exception("Work description with id " + id + " does not exist");
		}
		return workDescriptionRepo.findById(id).get();

	}

//	@Override
//	public ArrayList<KPIWorkDescription> filterAmountLessThan(int amount) throws Exception {
//		if (amount < 1) {
//			throw new Exception("Amount must be at least 1");
//		}
//		return workDescriptionRepo.findByAmountLessThan(amount);
//	}

	@Override
	public Page<KPIWorkDescription> findAllWorkDescriptionsPaginated(Pageable pageable) throws Exception {
		if (workDescriptionRepo.count() == 0) {
			throw new Exception("no work descriptions");
		}
		return workDescriptionRepo.findAll(pageable);
	}

	@Override
	public ArrayList<KPIWorkDescription> filterByPriority(Priority priority) throws Exception {
	    if (priority == null) {
	        throw new Exception("Priority cannot be empty");
	    }
	    return workDescriptionRepo.findByPriority(priority);
	}
	}
