package eu.virac.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "DepartmentTable")

public class Department {

	@Column(name = "Did")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long did;

	@Column(name = "description")
	@NotEmpty
	@NotNull
	private String description;

	@Column(name = "goals")
	@NotEmpty
	@NotNull
	private String goals;

	@OneToMany(mappedBy = "department")
	private Collection<Users> user = new ArrayList<Users>();

	@OneToMany(mappedBy = "department")
	private Collection<KPIWorkDescription> workDescription = new ArrayList<>();

	public Department(long did, String description, String goals) {
		setDescription(description);
		setGoals(goals);
	}
}
