package eu.virac.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.model.KPIWorkDescription;
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
		return (ArrayList<KPIWorkDescription>) workDescriptionRepo.findAll();
	}

	@Override
	public ArrayList<KPIWorkDescription> filterByDate(LocalDate date) throws Exception {
		if (date == null) {
			throw new Exception("Date cannot be empty");
		}
		return workDescriptionRepo.findByDate(date);
	}

	@Override
	public ArrayList<KPIWorkDescription> filterByAmountGreaterThan(int amount) throws Exception {
		if (amount < 1) {
			throw new Exception("Amount must be at least 1");
		}
		return workDescriptionRepo.findByAmountGreaterThan(amount);
	}

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
		if (workDescriptionRepo.existsByDescription(workDescription.getDescription())) {
			throw new Exception("Work description with this name already exists");
		}
		if (workDescription.getDescription() == null) {
			throw new Exception("invalid input");
		}
		return workDescriptionRepo.save(workDescription);
	}

	@Override
	public KPIWorkDescription updateWorkDescription(long id, String description, LocalDate date, int amount)
			throws Exception {
		KPIWorkDescription workDescriptionToUpdate = findWorkDescriptionById(id);

		if (description == null || description.isEmpty() || date == null || amount < 1) {
			throw new Exception("Invalid input data");
		}

		if (description != null) {
			workDescriptionToUpdate.setDescription(description);
		}
		if (date != null) {
			workDescriptionToUpdate.setDate(date);
		}
		if (amount >= 1) {
			workDescriptionToUpdate.setAmount(amount);
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
		if (workDescriptionRepo.count() == 0) {
			throw new Exception("Work description table is empty");
		}
		if (id < 1) {
			throw new Exception("Id cannot be negative or 0");
		}
		if (!workDescriptionRepo.existsById(id)) {
			throw new Exception("Work description with id " + id + " does not exist");
		}
		return workDescriptionRepo.findById(id).get();
	}

	@Override
	public ArrayList<KPIWorkDescription> findByAmountLessThan(int amount) throws Exception {
		if (amount < 1) {
			throw new Exception("Amount must be at least 1");
		}
		return workDescriptionRepo.findByAmountLessThan(amount);
	}

}
