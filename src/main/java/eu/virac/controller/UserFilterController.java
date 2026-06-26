package eu.virac.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.virac.model.Users;
import eu.virac.service.IUserFilter;

@Controller
@RequestMapping("/user/filter")
public class UserFilterController {

	@Autowired
	private IUserFilter Ufilterservice;

	@GetMapping("/name/{letter}") // localhost:8080/user/filter/name/M
	public String getUsersByFirstLetter(@PathVariable(name = "letter") String letter, Model model) {
		try {
			ArrayList<Users> usersFromDB = Ufilterservice.filterByStartingWith(letter);

			model.addAttribute("package", usersFromDB);
			model.addAttribute("info", "Users with name starting letter: " + letter);
			return "show-all-users";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	// FILTER BY EMAIL
	@GetMapping("/email/{email}") // localhost:8080/user/filter/email/gmail
	public String getUsersByEmail(@PathVariable(name = "keyword") String Emailkeyword, Model model) {
		try {
			ArrayList<Users> usersFromDB = Ufilterservice.filterByEmail(Emailkeyword);

			model.addAttribute("package", usersFromDB);
			model.addAttribute("info", "Users with email: " + Emailkeyword);
			return "show-all-users";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
