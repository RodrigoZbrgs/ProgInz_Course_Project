package eu.virac.service;
import java.time.LocalDate;
import java.util.ArrayList;
import eu.virac.model.KPIWorkDescription;

public interface IWorkDescriptionService {
	 public abstract ArrayList<KPIWorkDescription> findAllWorkDescriptions() throws Exception;
	 
	 public abstract KPIWorkDescription findWorkDescriptionById(long id) throws Exception;
	    
	    public abstract KPIWorkDescription addWorkDescription(KPIWorkDescription workDescription) throws Exception;
	    
	    public abstract KPIWorkDescription updateWorkDescription(long id, String description, LocalDate date, int amount) throws Exception;
	    
	    public abstract void deleteWorkDescription(long id) throws Exception;
}
