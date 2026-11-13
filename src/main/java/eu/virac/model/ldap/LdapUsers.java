package eu.virac.model.ldap;

import javax.naming.Name;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entry(base = "ou=users", objectClasses = { "inetOrgPerson", "person", "top" })
public final class LdapUsers {

	@Column(name="Dn")
	@Id
	private Name dn;

	@Column(name="Username")
	@Attribute(name = "uid")
	private String username;

	@Column(name="Name")
	@Attribute(name = "givenName")
	private String name;

	@Column(name="Surname")
	@Attribute(name = "sn")
	private String surname;

	@Column(name="Email")
	@Attribute(name = "mail")
	private String email;

	@Column(name="Password")
	@Attribute(name = "userPassword")
	private String password;

	public LdapUsers(String username, String name, String surname, String email, String password) {
		setUsername(username);
		setName(name);
		setSurname(surname);
		setEmail(email);
		setPassword(password);
	}
}