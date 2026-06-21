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
public class KPICategories {
    @Column(name = "IDCat")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private long IDCat;

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
    public KPICategories(String Category, String Description) {
        setCategory(Category);
        setDescription(Description);
    }
}