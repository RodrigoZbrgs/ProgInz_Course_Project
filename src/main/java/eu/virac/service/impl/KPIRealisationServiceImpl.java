package eu.virac.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.model.KPIRealisation;
import eu.virac.repo.IKPIRealisationRepo;
import eu.virac.service.IKPIRealisationFilter;
import eu.virac.service.IKPIRealisationService;

@Service
public class KPIRealisationServiceImpl implements IKPIRealisationService, IKPIRealisationFilter {

	@Autowired
	private IKPIRealisationRepo realRepo;

	@Override
	public ArrayList<KPIRealisation> findAllRealisations() throws Exception {
		if (realRepo.count() == 0) {
			throw new Exception("Realisation amount is empty");
		}
		return (ArrayList<KPIRealisation>) realRepo.findAll();
	}

	@Override
	public KPIRealisation findRealisationById(long id) throws Exception {
		if (realRepo.count() == 0) {
			throw new Exception("Realisation amount is empty");
		}
		if (id < 1) {
			throw new Exception("Id cannot be negative or 0");
		}
		if (!realRepo.existsById(id)) {
			throw new Exception("Realisation with id " + id + " does not exist");
		}
		return realRepo.findById(id).get();
	}

	@Override
	public KPIRealisation addRealisation(KPIRealisation kpiRealisation) throws Exception {
		if (kpiRealisation == null) {
			throw new Exception("Realisation data is not usable");
		}
		return realRepo.save(kpiRealisation);
	}

	@Override
	public KPIRealisation updateRealisation(long id, LocalDate realisationdate, String text, String comment) throws Exception {
		KPIRealisation realisationToUpdate = findRealisationById(id);

		if (realisationdate == null || text.isEmpty() || comment == null) {
			throw new Exception("Invalid input data");
		}

		if (realisationdate != null) {
			realisationToUpdate.setRealisationdate(realisationdate);
			;
		}
		if (text != null) {
			realisationToUpdate.setTextvalue(text);
			;
		}
		if (comment != null) {
			realisationToUpdate.setComment(comment);
		}

		return realRepo.save(realisationToUpdate);
	}

	@Override
	public void deleteRealisationById(long id) throws Exception {
		if (id < 1) {
			throw new Exception("Id cannot be negative or 0");
		}
		if (!realRepo.existsById(id)) {
			throw new Exception("Work description with id " + id + " does not exist");
		}
		realRepo.deleteById(id);

	}

	@Override
	public ArrayList<KPIRealisation> filterByRealisationDate(LocalDate realisationdate) throws Exception {
		if (realisationdate == null) {
			throw new Exception("Incorrect parameters");
		}

		if (realRepo.count() == 0) {
			throw new Exception("DB has no projects, so can not be filtered");
		}

		ArrayList<KPIRealisation> filteredRealisations = realRepo.findByRealisationdate(realisationdate);

		if (filteredRealisations.isEmpty()) {
			throw new Exception("No project on " + realisationdate);
		}
		return filteredRealisations;
	}

}
