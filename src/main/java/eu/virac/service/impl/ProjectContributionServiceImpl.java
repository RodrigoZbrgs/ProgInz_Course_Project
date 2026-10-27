package eu.virac.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.virac.model.ProjectContribution;
import eu.virac.repo.IProjectContributionRepo;
import eu.virac.service.IProjectContributionService;

@Service
public class ProjectContributionServiceImpl implements IProjectContributionService {

	@Autowired
	private IProjectContributionRepo projConRepo;
	
	@Override
	public ArrayList<ProjectContribution> selectAllContributions() throws Exception {
		if (projConRepo.count() == 0) {
			throw new Exception("Projekta ieguldījuma tabula ir tukša");
		}
		return (ArrayList<ProjectContribution>) projConRepo.findAll();
	}
	
	@Override
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
	public void deleteContributionById(int id) throws Exception {
		ProjectContribution contributionForDeleting = selectContributionById(id);
		projConRepo.delete(contributionForDeleting);
	}
	
	@Override
	public ProjectContribution insertNewContribution(ProjectContribution newContribution) throws Exception {
		if(newContribution == null) {
			throw new Exception("Dati nav pieejami, jo nav referenci");
		}
	}
}
	
	
