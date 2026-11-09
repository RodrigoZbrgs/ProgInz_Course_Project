package eu.virac.controller.ldap;

import eu.virac.repo.IUsersRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LdapController {

	private IUsersRepo usersRepo;

	public LdapController(IUsersRepo usersRepo) {
		this.usersRepo = usersRepo;
	}

	@GetMapping("/ldap")
	public String showLdapPage(Model model) {
		model.addAttribute("users", usersRepo.findAll());
		return "ldap-users";
	}
}