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
@Table(name = "KPIRealisationTable")
public class KPIRealisation {
	@Column(name = "Idkpir")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idkpir;
	
	//TODO uztaisīt visas validācijas
	@Column(name = "Realisationdate")
	private LocalDate realisationdate;
	
	@Column(name = "Textvalue")
	private String textvalue;
	
	@Column(name = "Comment")
	private String comment;
	
	//TODO uztaisīt saiti uz KPI darba aprakstu
	
	public KPIRealisation(LocalDate realisationdate, String textvalue, String comment) {
		setRealisationdate(realisationdate);
		setTextvalue(textvalue);
		setComment(comment);
	}
	
}
