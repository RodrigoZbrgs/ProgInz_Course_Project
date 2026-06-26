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
import eu.virac.service.IUserService;

@Controller
@RequestMapping("/user/crud")
public class UserCRUDController {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/all") // localhost:8080/user/crud/all
	public String getControllerRetrievAllUsers(Model model) {
		try {
			ArrayList<Users> usersFromDB = userService.selectAllUser();
			model.addAttribute("package", usersFromDB );
			return "show-all-users";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	@GetMapping("/all/{id}")// localhost:8080/user/crud/all/1
	public String getControllerSelectUsersById(@PathVariable(name = "id") int id, Model model) {
		try {
			Users usersFromDB = userService.selectUsersById(id);
			model.addAttribute("package", usersFromDB);
			return "show-one-user";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}

	}
	
	@GetMapping("/remove/{id}")// localhost:8080/user/crud/all/1
	public String getControllerUserForRemoval(@PathVariable(name = "id") int id, Model model) {
		try {
			userService.deleteUserById(id);
			ArrayList<Users> usersFromDB= userService.selectAllUser();
			model.addAttribute("package", usersFromDB);
			return "show-all-users";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	@GetMapping("/add")// localhost:8080/user/crud/add
	public String getControllerForUserAdding(Model model) {
		try {

			model.addAttribute("project", new Users());
			return "add-one-user";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	@GetMapping("/update/{id}")// localhost:8080/user/crud/all/2
	public String retrieveUpdateuserById(@PathVariable(name = "id") int id, Model model) {
		try {
			Users usersFromDB = userService.selectUsersById(id);
			model.addAttribute("project", usersFromDB);
			return "update-one-user";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
