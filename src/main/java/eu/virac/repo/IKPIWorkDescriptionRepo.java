package eu.virac.repo;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.KPIWorkDescription;

public interface IKPIWorkDescriptionRepo extends CrudRepository<KPIWorkDescription, Long> {

}
