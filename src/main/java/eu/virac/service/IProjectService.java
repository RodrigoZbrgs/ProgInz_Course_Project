package eu.virac.service;

import java.time.LocalDate;
import java.util.ArrayList;

import eu.virac.model.ProjectInformation;

public interface IProjectService extends ICrudBaseService<ProjectInformation> {

	public abstract ProjectInformation selectProjectById(long id) throws Exception;

	public abstract ProjectInformation insertNewProject(ProjectInformation newProject) throws Exception;

	public abstract void updateByidpi(long id, String projectNumber, String projectName, LocalDate startingDate, LocalDate endingDate)
			throws Exception;

	public abstract ArrayList<ProjectInformation> selectAllProjects() throws Exception;

	public abstract void deleteProjectById(int id) throws Exception;

}
