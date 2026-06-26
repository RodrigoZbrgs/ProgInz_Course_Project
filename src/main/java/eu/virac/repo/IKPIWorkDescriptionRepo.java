package eu.virac.repo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.KPIWorkDescription;

public interface IKPIWorkDescriptionRepo extends CrudRepository<KPIWorkDescription, Long> {
	public abstract boolean existsByDescription(String Description);
	public abstract ArrayList<KPIWorkDescription> findByDescriptionContainingIgnoreCase(String keyword);
	public abstract ArrayList<KPIWorkDescription> findByDate(LocalDate date);
	public abstract ArrayList<KPIWorkDescription> findByAmountGreaterThan(int amount);
	public abstract ArrayList<KPIWorkDescription> findByAmountLessThan(int amount);
}
