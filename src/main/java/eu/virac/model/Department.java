package eu.virac.model;

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
@ToString
@Entity
@Table(name="NodalasTable")
public class nodala {
	@Column(name="Idn")
	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idn;
	@Column(name = "Description")
	@NotEmpty
	@NotNull
	private String Description;
	@Column(name = "Goals")
	@NotEmpty
	@NotNull
	private String Goals;
	
	public nodala(long Idn, String Description, String Goals) {
		setDescription(Description);
		setGoals(Goals);
	}
}
