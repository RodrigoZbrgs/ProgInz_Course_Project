package eu.virac.repo;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.KPI_Statuss;

public interface IKPI_StatusRepo extends CrudRepository<KPI_Statuss, Long> {

}
