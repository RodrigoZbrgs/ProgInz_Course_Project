package eu.virac.repo;
import org.springframework.data.repository.CrudRepository;
import eu.virac.model.KPICategories;

public interface IKPICategoriesRepo extends CrudRepository<KPICategories, Long> {
	
}
