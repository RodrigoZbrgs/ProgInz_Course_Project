package eu.virac.repo;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.ProjectContribution;

public interface IProjectInformationRepo extends CrudRepository<ProjectContribution, Long> {

}
