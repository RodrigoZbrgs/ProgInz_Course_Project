package eu.virac.model.ldap;

import javax.naming.Name;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entry(base = "ou=users", objectClasses = { "inetOrgPerson", "person", "top" })
public class LdapUsers {

	@Id
	private Name dn;

	@Attribute(name = "uid")
	private String username;

	@Attribute(name = "givenName")
	private String name;

	@Attribute(name = "sn")
	private String surname;

	@Attribute(name = "mail")
	private String email;

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