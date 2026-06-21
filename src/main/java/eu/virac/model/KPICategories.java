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
@Table(name = "KPI Categories")
public class KPICategories {
	private long IDCat;

	@Column(name = "Category", unique = true)
	@NotNull
	@NotEmpty
	private String Category;

	@Column(name = "Description", unique = true)
	@NotNull
	@NotEmpty

	private String Description;

	public KPICategories(String Category, String Description) {
		setCategory(Category);
		setDescription(Description);
	}
}
