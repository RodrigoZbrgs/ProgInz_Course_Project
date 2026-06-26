package eu.virac.repo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.ProjectInformation;

public interface IProjectInformationRepo extends CrudRepository<ProjectInformation, Long> {

	public abstract boolean existsByProjectName(String projectName);

	public abstract ArrayList<ProjectInformation> findByProjectNameContaining(String keyword);

	public abstract ArrayList<ProjectInformation> findByActive(Boolean activity);

	public abstract ArrayList<ProjectInformation> findByEndingDate(LocalDate endingDate);

	public abstract ArrayList<ProjectInformation> findByStartingDate(LocalDate startingDate);

}
