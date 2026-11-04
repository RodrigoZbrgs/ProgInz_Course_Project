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
@Entity
@Table(name = "KPISubcategoriesTable")
public class KPI_Subcategories {
	@Column(name = "Idsc")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idsc;

	@Column(name = "Title", unique = true)
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[A-Ža-ž0-9 ]{3,40}")
	private String title;

	@Column(name = "Description", unique = true)
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Ža-ž0-9 ]{3,300}")
	private String description;

	@ManyToOne
	@JoinColumn(name = "Idc")
	private KPICategories category;

	@OneToMany(mappedBy = "subCategory")
	private Collection<KPIWorkDescription> workDescription = new ArrayList<>();

	public KPI_Subcategories(String title, String description, KPICategories category) {
		setCategory(category);
		setDescription(description);
		setTitle(title);
		
	}
}