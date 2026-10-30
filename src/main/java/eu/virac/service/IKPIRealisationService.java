package eu.virac.service;

import java.time.LocalDate;

import eu.virac.model.KPIRealisation;

public interface IKPIRealisationService extends ICrudBaseService<KPIRealisation> {

	public abstract KPIRealisation updateRealisation(long id, LocalDate realisationdate, String text, String comment)
			throws Exception;


}
