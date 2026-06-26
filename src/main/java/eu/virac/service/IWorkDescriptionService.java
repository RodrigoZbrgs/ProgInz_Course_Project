package eu.virac.service;
import java.util.ArrayList;
import java.util.Optional;
import eu.virac.model.KPIWorkDescription;

public interface IWorkDescriptionService {
	 public abstract ArrayList<KPIWorkDescription> findAllWorkDescriptions();
	 
	    public abstract Optional<KPIWorkDescription> findWorkDescriptionById(long id);
	    
	    public abstract KPIWorkDescription saveWorkDescription(KPIWorkDescription workDescription);
	    
	    public abstract KPIWorkDescription updateWorkDescription(long id, KPIWorkDescription workDescription);
	    
	    public abstract void deleteWorkDescription(long id);
}
