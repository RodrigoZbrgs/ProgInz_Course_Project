package eu.virac.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@NotNull
	private LocalDate statusDate;

	@ManyToOne
    @JoinColumn(name = "idkpiwd")
    private KPIWorkDescription workDescription;

    @ManyToOne
    @JoinColumn(name = "uid")
    private Users user;
    
	public KPI_Statuss(String status, LocalDate statusDate) {
		setStatus(status);
		setStatusDate(statusDate);
	}
}
