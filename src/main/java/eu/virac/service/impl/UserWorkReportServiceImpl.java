package eu.virac.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.model.KPIWorkDescription;
import eu.virac.repo.IKPIWorkDescriptionRepo;
import eu.virac.service.IUserWorkReportService;

@Service
public class UserWorkReportServiceImpl implements IUserWorkReportService {

	@Autowired
	private IKPIWorkDescriptionRepo workDescriptionRepo;

	@Override
	public ArrayList<KPIWorkDescription> getUserWorkReport(String username) throws Exception {
		if (username == null || username.isEmpty()) {
			throw new Exception("Username cannot be empty");
		}
		ArrayList<KPIWorkDescription> reports = workDescriptionRepo.findByUserUsername(username);
		if (reports.size() == 0) {
			throw new Exception("No work descriptions were found for user: " + username);
		}
		return reports;
	}
}
