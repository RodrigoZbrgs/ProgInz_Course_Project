package eu.virac.model;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import eu.virac.model.Users;
	@Getter
	@Setter
	@NoArgsConstructor
	@ToString
	@Entity
	@Table(name = "MyAuthorityTable")
	public class MyAuthority {
		@Column(name = "Ida")
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Setter(value = AccessLevel.NONE)//priekš ids nebūs set funkcija
		private long ids;
		@Column(name = "Title", unique = true)
		@NotNull
		@NotEmpty
		@Pattern(regexp= "[A-Z_]{3,15}")
		private String title;
		@ManyToMany(fetch = FetchType.EAGER)
		@JoinTable(name = "AuthUserTable",
		inverseJoinColumns = @JoinColumn (name = "MyUserTable"),
		joinColumns = @JoinColumn(name = "MyAuthorityTable"))
		@ToString.Exclude
		private Collection<Users> users = new ArrayList<Users>();
		public void addUser (Users user) {
			if(!users.contains(user)) {
				users.add(user);
			}
		}
		public void removeUser(Users user) {
			if (users.contains(user)) {
				users.remove(user);
			}
		}
		public MyAuthority(String title) {
			setTitle(title);
		}
	}

