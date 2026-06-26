package eu.virac.service;

import java.time.LocalDate;
import java.util.ArrayList;

import eu.virac.model.KPIWorkDescription;

public interface IWorkDescriptionsFilter {
	public abstract ArrayList<KPIWorkDescription> filterByDescriptionContaining(String keyword);
    public abstract ArrayList<KPIWorkDescription> filterByDate(LocalDate date);
    public abstract ArrayList<KPIWorkDescription> filterByAmountGreaterThan(int amount);
}

