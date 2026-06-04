package eu.virac.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	private long projectNumber;
	
	@Column(name = "Project_name")
	private String projectName;
	
	@Column(name = "Starting_date")
	private LocalDate startingDate;
	
	@Column(name = "Ending_date")
	private LocalDate endingDate;
	
	
	public ProjectInformation(long projectNumber, String projectName, LocalDate startingDate, LocalDate endingDate) {
		setProjectNumber(projectNumber);
		setProjectName(projectName);
		setStartingDate(startingDate);
		setEndingDate(endingDate);
	}
}
