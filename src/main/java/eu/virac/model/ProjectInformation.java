package eu.virac.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "ProjectInformationTable")

public class ProjectInformation {

	@Column(name = "Idpi")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idpi;
	
	//TODO validacijas
	
	@Column(name = "Project_number")
	@NotNull
	@Pattern(regexp = "[P]{1}[0-9]{3,40}")
	private long projectNumber;
	
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[A-Ža-Ž]{3,40}")
	@Column(name = "Project_name")
	private String projectName;
	
	@NotEmpty
	@Column(name = "Starting_date")
	private LocalDate startingDate;
	
	@NotEmpty
	@Column(name = "Ending_date")
	private LocalDate endingDate;
	
	
	public ProjectInformation(long projectNumber, String projectName, LocalDate startingDate, LocalDate endingDate) {
		setProjectNumber(projectNumber);
		setProjectName(projectName);
		setStartingDate(startingDate);
		setEndingDate(endingDate);
	}
}
