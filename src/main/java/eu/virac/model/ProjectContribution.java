package eu.virac.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import eu.virac.model.enums.EmployeePositions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "ProjectContributionTable")

public class ProjectContribution {

	@Column(name = "Idpc")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idpc;

	@NotNull
	@Column(name = "Position_name")
	private EmployeePositions employeePosition;

	@NotNull
	@Column(name = "Starting_date")
	private LocalDate startingDate;

	@NotNull
	@Column(name = "Ending_date")
	private LocalDate endingDate;

	@ManyToOne
	@JoinColumn(name = "idpi")
	private ProjectInformation projectInformation;

	@ManyToOne
	@JoinColumn(name = "uid")
	@NotNull
	private Users user;

	@OneToMany(mappedBy = "contributions")
	private Collection<KPIWorkDescription> workDescription = new ArrayList<>();

	public ProjectContribution(EmployeePositions employeePosition, LocalDate startingDate, LocalDate endingDate) {
		setEmployeePosition(employeePosition);
		setStartingDate(startingDate);
		setEndingDate(endingDate);
	}
}
