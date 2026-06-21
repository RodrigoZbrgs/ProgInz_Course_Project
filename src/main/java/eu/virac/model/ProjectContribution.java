package eu.virac.model;

import java.time.LocalDate;
import eu.virac.model.enums.EmployeePositions;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
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
	
	@NotEmpty
	@Column(name = "Position_name")
	private EmployeePositions employeePosition;
	
	@NotEmpty
	@Column(name = "Starting_date")
	private LocalDate startingDate;
	
	@NotEmpty
	@Column(name = "Ending_date")
	private LocalDate endingDate;
	
	@ManyToOne
	@JoinColumn(name = "idpi")
	private ProjectInformation projectInformation;
	
//	@ManyToOne
//	@JoinColumn(name = "idu")
//	private User user;
	
	public ProjectContribution(EmployeePositions employeePosition, LocalDate startingDate, LocalDate endingDate, ProjectInformation projectInformation) {
		setEmployeePosition(employeePosition);
		setStartingDate(startingDate);
		setEndingDate(endingDate);
//		setUser(user);
		setProjectInformation(projectInformation);
	}
}
