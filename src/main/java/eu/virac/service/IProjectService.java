package eu.virac.service;

import java.time.LocalDate;
import java.util.ArrayList;

import eu.virac.model.ProjectInformation;

public interface IProjectService extends ICrudBaseService<ProjectInformation> {
	
	public abstract void updateByidpi(long id, String projectNumber, String projectName, LocalDate startingDate,
			LocalDate endingDate) throws Exception;

}
