package eu.virac.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.virac.model.Department;
import eu.virac.model.Users;
import eu.virac.repo.IUsersRepo;
import eu.virac.service.ICrudBaseService;
import eu.virac.service.IUserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/crud")
public class UserCRUDController {

	@Autowired
	private IUserService userService;

	@GetMapping("/all") // localhost:8080/user/crud/all
	public String getControllerRetrievAllUsers(Model model) {
		try {
			ArrayList<Users> usersFromDB = userService.selectAll();
			model.addAttribute("package", usersFromDB);
			return "show-all-users";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/user/crud/all/1
	public String getControllerSelectUsersById(@PathVariable(name = "id") int id, Model model) {
		try {
			Users usersFromDB = userService.retrieve(id);
			model.addAttribute("package", usersFromDB);
			return "show-one-user";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}

	}

	@GetMapping("/remove/{id}") // localhost:8080/user/crud/all/1
	public String getControllerUserForRemoval(@PathVariable(name = "id") int id, Model model) {
		try {
			userService.delete(id);
			ArrayList<Users> usersFromDB = userService.selectAll();
			model.addAttribute("package", usersFromDB);
			return "show-all-users";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/add") // localhost:8080/user/crud/add
	public String getControllerForUserAdding(Model model) {
		Users user = new Users();
	    user.setDepartment(new Department());
	    model.addAttribute("user", user);
	    return "add-one-user";

	}

	@PostMapping("/add")
	public String postControllerForUserAdding(@Valid @ModelAttribute("user") Users user, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        // Log validation errors to your console/logger
	        result.getAllErrors().forEach(error -> {
	            System.out.println("Validation Error: " + error.toString());
	        });
	        return "add-one-user";
	    }
	    try {
	        userService.create(user);
	        return "redirect:/user/crud/all";
	    } catch (Exception e) {
	        model.addAttribute("package", e.getMessage());
	        return "error-page";
	    }
	}

	@GetMapping("/update/{id}") // localhost:8080/user/crud/update/2
	public String retrieveUpdateuserById(@PathVariable(name = "id") int id, Model model) {
		try {
			Users usersFromDB = userService.retrieve(id);
			model.addAttribute("user", usersFromDB);
			return "update-one-user";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update/{id}")
	public String postControllerForUpdateUser(@PathVariable(name = "id") int id, Users user, Model model) {
		try {
			userService.update(id, user.getName(), user.getSurname(), user.getEmail(), user.getPassword(),
					user.getDepartment());
			return "redirect:/user/crud/all/" + id;
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
