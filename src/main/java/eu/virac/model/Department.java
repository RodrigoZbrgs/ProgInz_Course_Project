package eu.virac.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "department_table")

public class Department {
	
	@Column(name = "did")
	@Id
	@ManyToOne
	private long did;

	@Column(name = "description")
	@NotEmpty
	@NotNull
	private String description;
	
	@Column(name = "goals")
	@NotEmpty
	@NotNull
	private String goals;

	public Department(long did, String description, String goals) {
		setDescription(description);
		setGoals(goals);
	}
}
