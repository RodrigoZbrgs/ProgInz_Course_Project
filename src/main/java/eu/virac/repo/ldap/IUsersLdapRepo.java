package eu.virac.repo.ldap;

import org.springframework.data.repository.CrudRepository;

import eu.virac.model.ldap.LdapUsers;

public interface IUsersLdapRepo extends CrudRepository<LdapUsers, Long> {
	LdapUsers findByUsername(String username);
}
