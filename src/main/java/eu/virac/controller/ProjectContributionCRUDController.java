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

import eu.virac.model.ProjectContribution;
import eu.virac.repo.IUsersRepo;
import eu.virac.service.IProjectContributionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contributions/crud")
public class ProjectContributionCRUDController {
	@Autowired
	private IProjectContributionService projConService;

	@Autowired
	IUsersRepo userRepo;

	@GetMapping("/all")
	public String getControllerRetrievAllContributions(Model model) {
		try {
			ArrayList<ProjectContribution> contributionsFromDB = projConService.selectAll();
			model.addAttribute("package", contributionsFromDB);
			return "show-all-contributions";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/all/{id}")
	public String getControllerSelectContributionById(@PathVariable(name = "id") int id, Model model) {
		try {
			ProjectContribution contributionsFromDB = projConService.retrieve(id);
			model.addAttribute("package", contributionsFromDB);
			return "show-one-contribution";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}

	}

	@GetMapping("/remove/{id}")
	public String getControllerContributionForRemoval(@PathVariable(name = "id") int id, Model model) {
		try {
			projConService.delete(id);
			ArrayList<ProjectContribution> contributionsFromDB = projConService.selectAll();
			model.addAttribute("package", contributionsFromDB);
			return "show-all-contributions";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/add")
	public String getControllerForContributionAdding(Model model) {
		try {

			model.addAttribute("projectcontribution", new ProjectContribution());
			return "add-one-contribution";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/add")
	public String postControllerForContributionAdding(@Valid ProjectContribution contribution, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "add-one-contribution";

		} else {
			try {

				projConService.create(contribution);
				return "redirect:/contributions/crud/all";

			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}

	}

	@GetMapping("/update/{id}")
	public String retrieveUpdateContributionById(@PathVariable(name = "id") int id, Model model) {
		try {
			ProjectContribution contributionsFromDB = projConService.retrieve(id);
			model.addAttribute("contribution", contributionsFromDB);
			return "update-one-contribution";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update/{id}")
	public String postControllerForUpdateById(@PathVariable(name = "id") int id, ProjectContribution contribution,
			Model model) {
		try {
			projConService.update(id, contribution.getEmployeePosition(), contribution.getStartingDate(),
					contribution.getEndingDate());
			return "redirect:/contributions/crud/all/" + id;
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
