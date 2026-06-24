package eu.virac.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class KPIWorkDescription {

	
    @ManyToOne
    @JoinColumn(name = "uid")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "idsc")
    private KPI_Subcategories subCategory;

    @ManyToOne
    @JoinColumn(name = "idpc")
    private ProjectContribution contributions;

    @ManyToOne
    @JoinColumn(name = "did")
    private Department department;
	
    @OneToMany(mappedBy = "workDescription")
    private Collection<KPI_Statuss> kpiStatuss = new ArrayList<KPI_Statuss>();

    @OneToMany(mappedBy = "workDescription")
    private Collection<KPIRealisation> kpiRealisation = new ArrayList<KPIRealisation>();
}
