package eu.virac.repo;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.KPIRealisation;

public interface IKPIRealisationRepo extends CrudRepository<KPIRealisation, Long> {

	ArrayList<KPIRealisation> findByRealisationdate(LocalDate realisationdate);


}
