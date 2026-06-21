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
@NoArgsConstructor
@ToString
@Entity
@Table(name="KPI Categories")
<<<<<<< HEAD:src/main/java/eu/virac/model/KPI_kategorijas.java
public class KPI_kategorijas {
	@Column(name = "/idc")
=======
public class KPICategories {
	@Column(name = "IDCat")
>>>>>>> 08ad06d13cc21859cd462d234641fed90bcbc6bd:src/main/java/eu/virac/model/KPICategories.java
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idc;
	
	@Column(name = "Category", unique = true)
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[A-Ža-ž0-9 ]{3,40}")
	private String Category;
	
	@Column(name = "Description", unique = true)
	@NotNull
	@NotEmpty
	@Pattern(regexp = "{A-Ža-ž0-9 ]{3,300}")
	private String Description;
<<<<<<< HEAD:src/main/java/eu/virac/model/KPI_kategorijas.java
	
	public KPI_kategorijas(String Category, String Description) {
=======
	public KPICategories(String Category, String Description) {
>>>>>>> 08ad06d13cc21859cd462d234641fed90bcbc6bd:src/main/java/eu/virac/model/KPICategories.java
	    setCategory(Category);
	    setDescription(Description);
	}
}
