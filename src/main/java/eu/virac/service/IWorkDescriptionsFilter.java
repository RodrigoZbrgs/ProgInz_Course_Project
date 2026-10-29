package eu.virac.service;

import java.time.LocalDate;
import java.util.ArrayList;

import eu.virac.model.KPIWorkDescription;
import eu.virac.model.enums.Priority;

public interface IWorkDescriptionsFilter {
	public abstract ArrayList<KPIWorkDescription> filterByDescription(String keyword) throws Exception;

	public abstract ArrayList<KPIWorkDescription> filterByDate(LocalDate date) throws Exception;

	public abstract ArrayList<KPIWorkDescription> filterByPriority(Priority priority) throws Exception;
//
//	public abstract ArrayList<KPIWorkDescription> filterAmountLessThan(int amount) throws Exception;

	public abstract ArrayList<KPIWorkDescription> filterByAmountGreaterThan(int amount) throws Exception;

	public abstract ArrayList<KPIWorkDescription> filterAmountLessThan(int amount);
}
