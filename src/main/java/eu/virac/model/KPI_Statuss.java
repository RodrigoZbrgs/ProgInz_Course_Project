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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "KPI_Statuss")
public class KPI_Statuss {

	@Column(name = "KPI_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long kpiId;
	
	@Column(name = "Status")
	@NotEmpty
	@NotNull
	private String status;

	@Column(name = "Status_date")
	@NotEmpty
	@NotNull
	private LocalDate statusDate;


	// KPIDA_ID un L_ID japievieno caur tiem ManytoOne things 
	
	public KPI_Statuss(String status, LocalDate statusDate) {
		setStatus(status);
		setStatusDate(statusDate);
	}
}
