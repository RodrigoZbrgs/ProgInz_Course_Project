package eu.virac.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "KPIWorkDescriptionTable")
public class KPIWorkDescription {

	@Column(name = "Idkpiwd")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idkpiwd;

	@Column(name = "date")
	@NotNull
	private LocalDate date;

	@Column(name = "description", unique = true)
	@NotNull
	private String description;

	@ManyToOne
	@JoinColumn(name = "uid")
	private Users user;

	@Column(name = "amount")
	@Min(1)
	private int amount;

	@Column(name = "description", unique = true)
	@NotNull
	private String Description;

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

	public KPIWorkDescription(LocalDate date, int amount, String description) {
		setDate(date);
		setAmount(amount);
		setDescription(description);
	}

}
