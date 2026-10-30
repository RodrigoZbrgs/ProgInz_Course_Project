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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "DepartmentTable")
public class Department {

	@Column(name = "Did")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Setter(value = AccessLevel.NONE)
	private long did;

	@Column(name = "description")
	@NotEmpty
	@NotNull
	private String description;

	@Column(name = "goals")
	@NotEmpty
	@NotNull
	private String goals = "Default Goal"; // Avoids non-null constraint issues on save

	@ToString.Exclude
	@OneToMany(mappedBy = "department")
	private Collection<Users> user = new ArrayList<>();

	public Department(String description, String goals) {
		setDescription(description);
		setGoals(goals);
	}
}