package eu.virac.service;

import eu.virac.model.ProjectContribution;
import java.util.ArrayList;

public interface IProjectContributionService extends ICrudBaseService<ProjectContribution> {

	public abstract ArrayList<ProjectContribution> selectAllContributions() throws Exception;

	public abstract ProjectContribution selectContributionById(long id) throws Exception;
	
	public abstract void deleteContributionById(int id) throws Exception;

	public abstract ProjectContribution insertNewContribution(ProjectContribution newContribution) throws Exception;
	
}
