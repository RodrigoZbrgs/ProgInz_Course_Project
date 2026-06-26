package eu.virac.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "UsersTable")
@Entity
public class Users {

	@Column(name = "uid")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long uid;

	@Column(name = "name")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[a-ž]{2,20}([ ]{1}([A-Ž]{1}[a-ž]{2,20}))?")
	private String name;

	@Column(name = "surname")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[a-ž]{2,20}([ ]{1}([A-Ž]{1}[a-ž]{2,20}))?")
	private String surname;

	@Column(name = "email")
	@NotNull
	@NotEmpty
	private String email;

	@Column(name = "password")
	@NotNull
	@NotEmpty
	private String password;

	@ManyToOne
	@JoinColumn(name = "did")
	private Department department;

	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Collection<ProjectContribution> contributions = new ArrayList<ProjectContribution>();

	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Collection<KPIWorkDescription> kpiWorkDescription = new ArrayList<KPIWorkDescription>();

	@OneToMany(mappedBy = "user")
	@ToString.Exclude
	private Collection<KPI_Statuss> kpiStatuss = new ArrayList<KPI_Statuss>();

	public Users(String name, String surname, String email, String password) {
		setName(name);
		setSurname(surname);
		setEmail(email);
		setPassword(password);
	}
}