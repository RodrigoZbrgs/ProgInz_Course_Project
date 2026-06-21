package eu.virac.repo;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.ProjectContribution;

public interface IProjectContributionRepo extends CrudRepository<ProjectContribution, Long> {

}
