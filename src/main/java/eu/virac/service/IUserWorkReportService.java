package eu.virac.service;

import java.util.ArrayList;
import eu.virac.model.KPIWorkDescription;

public interface IUserWorkReportService {
	public abstract ArrayList<KPIWorkDescription> getUserWorkReport(String username) throws Exception;
}
