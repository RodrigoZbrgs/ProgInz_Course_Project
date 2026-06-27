package eu.virac.service;

import java.time.LocalDate;
import java.util.ArrayList;

import eu.virac.model.KPIRealisation;

public interface IKPIRealisationFilter {
	
	public abstract ArrayList<KPIRealisation> filterByRealisationDate(LocalDate realisationdate)throws Exception;
}
