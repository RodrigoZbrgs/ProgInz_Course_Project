package eu.virac.model;

import java.time.LocalDate;

import eu.virac.model.enums.WDStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "KPIStatusTable")
public class KPI_Statuss {

	@Column(name = "KPISTatusId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long kpiId;

	@Column(name = "Status")
	//TODO padomāt par notnull
	@Enumerated(EnumType.STRING)
	private WDStatus status;

	@Column(name = "Status_date")
	@NotNull
	private LocalDate statusDate;

	@ManyToOne
	@JoinColumn(name = "Idkpiwd")
	private KPIWorkDescription workDescription;



	public KPI_Statuss(WDStatus status, LocalDate statusDate, KPIWorkDescription workDescription) {
		setStatus(status);
		setStatusDate(statusDate);
		setWorkDescription(workDescription);

	}
}
