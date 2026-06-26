package eu.virac.service;

import java.time.LocalDate;
import java.util.ArrayList;

import eu.virac.model.ProjectInformation;

public interface IProjectFIlter {
	
	public abstract ArrayList<ProjectInformation> filterByKeyWord(String keyword) throws Exception;
	
	public abstract ArrayList<ProjectInformation> filterByActivity(Boolean activity) throws Exception;
	
	public abstract ArrayList<ProjectInformation> filterByStartingDate(LocalDate endingDate) throws Exception;

	public abstract ArrayList<ProjectInformation> filterByEndingDate(LocalDate endingDate) throws Exception;
}
	
