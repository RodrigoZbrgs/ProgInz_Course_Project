package eu.virac.service;

import java.time.LocalDate;
import java.util.ArrayList;

import eu.virac.model.KPIRealisation;

public interface IKPIRealisationService extends ICrudBaseService<KPIRealisation> {

	public abstract ArrayList<KPIRealisation> findAllRealisations() throws Exception;
	
	public abstract KPIRealisation findRealisationById(long id)throws Exception;
	
	public abstract KPIRealisation addRealisation(KPIRealisation kpiRealisation)throws Exception;
	
	public abstract KPIRealisation updateRealisation(long id, LocalDate date, String text, String comment)throws Exception;
	
	public abstract void deleteRealisationById(long id)throws Exception;
	
}
