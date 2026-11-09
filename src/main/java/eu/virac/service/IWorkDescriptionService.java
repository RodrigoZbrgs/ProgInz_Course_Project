package eu.virac.service;

import java.time.LocalDate;

import eu.virac.model.Department;
import eu.virac.model.KPIWorkDescription;
import eu.virac.model.ProjectInformation;
import eu.virac.model.enums.Priority;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IWorkDescriptionService extends ICrudBaseService<KPIWorkDescription> {

	public abstract void updateWorkDescription(long id, String description, LocalDate date,
			Priority priority, ProjectInformation information, Department department) throws Exception;

	public abstract Page<KPIWorkDescription> findAllWorkDescriptionsPaginated(Pageable pageable) throws Exception;

}