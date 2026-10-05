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
import jakarta.validation.constraints.NotNull;
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
@Table(name = "KPIRealisationTable")

public class KPIRealisation {

	@Column(name = "Idkpir")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idkpir;

	@Column(name = "Realisationdate")
	@NotNull
	private LocalDate date;

	@Column(name = "Textvalue")
	@NotNull
	private String textvalue;

	@Column(name = "Comment")
	@NotNull
	private String comment;

	@ManyToOne
	@JoinColumn(name = "idkpiwd")
	private KPIWorkDescription workDescription;

	public KPIRealisation(LocalDate date, String textvalue, String comment) {
		setDate(date);
		setTextvalue(textvalue);
		setComment(comment);
	}

}
