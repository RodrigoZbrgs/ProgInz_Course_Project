package eu.virac.repo;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.ProjectInformation;

public interface IProjectInformationRepo extends CrudRepository<ProjectInformation, Long> {

	public abstract boolean existsByProjectName(String projectName);

}
