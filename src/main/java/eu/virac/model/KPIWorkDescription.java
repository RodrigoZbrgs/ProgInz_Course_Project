package eu.virac.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name="KPI_work_description")
public class KPIWorkDescription {
	
	@Column(name = "kpiwdid")
	private long kpiwdid;
	
	@Column(name = "uid")
	private long uid;
	
	@Column(name = "scid")
	private long scid;
	
	@Column(name = "date")
	@NotNull
	@NotEmpty
	private String date;
	
	@Column(name = "amount")
	private int amount;
	
	@Column(name = "description", unique = true)
	@NotNull
	@NotEmpty
	private String Description;
	
	public KPIWorkDescription(String date, int amount, String description) {
	    setDate(date);
	    setAmount(amount);
	    setDescription(description);
	}
}
