package eu.virac.repo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import eu.virac.model.KPIWorkDescription;
import eu.virac.model.enums.Priority;

public interface IKPIWorkDescriptionRepo extends CrudRepository<KPIWorkDescription, Long>,PagingAndSortingRepository<KPIWorkDescription, Long> {
	public abstract boolean existsByDescription(String Description);
	public abstract ArrayList<KPIWorkDescription> findByDescriptionContainingIgnoreCase(String keyword);
	public abstract ArrayList<KPIWorkDescription> findByDate(LocalDate date);
	public abstract ArrayList<KPIWorkDescription> findByPriority(Priority priority);
//	public abstract ArrayList<KPIWorkDescription> findByAmountLessThan(Priority priority);
}
