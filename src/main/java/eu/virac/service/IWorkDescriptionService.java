package eu.virac.service;

import java.time.LocalDate;
import java.util.ArrayList;
import eu.virac.model.KPIWorkDescription;
import eu.virac.model.Users;
import eu.virac.model.enums.Priority;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IWorkDescriptionService {
	public abstract ArrayList<KPIWorkDescription> findAllWorkDescriptions() throws Exception;

	public abstract KPIWorkDescription findWorkDescriptionById(long id) throws Exception;

	public abstract KPIWorkDescription addWorkDescription(KPIWorkDescription workDescription) throws Exception;

	public abstract KPIWorkDescription updateWorkDescription(long id, String description, LocalDate date, Priority priority, Users user)
			throws Exception;

	public abstract void deleteWorkDescription(long id) throws Exception;
	public abstract Page<KPIWorkDescription> findAllWorkDescriptionsPaginated(Pageable pageable) throws Exception;
}
