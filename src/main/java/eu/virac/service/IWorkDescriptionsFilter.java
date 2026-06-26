package eu.virac.service;

import java.time.LocalDate;
import java.util.ArrayList;

import eu.virac.model.KPIWorkDescription;

public interface IWorkDescriptionsFilter {
	public abstract ArrayList<KPIWorkDescription> filterByDescription(String keyword) throws Exception;
	
    public abstract ArrayList<KPIWorkDescription> filterByDate(LocalDate date) throws Exception;
    
    public abstract ArrayList<KPIWorkDescription> filterByAmountGreaterThan(int amount) throws Exception;
    
    public abstract ArrayList<KPIWorkDescription> filterByAmountLesserThan(int amount) throws Exception;
}

