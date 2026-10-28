package eu.virac.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.virac.model.ProjectContribution;
import eu.virac.model.enums.EmployeePositions;
import eu.virac.service.IProjectContributionFilter;

@Controller
@RequestMapping("contributions/filter")
public class ProjectContributionFilterController {

	@Autowired
	private IProjectContributionFilter conFilter;
	
	@GetMapping("/position/{position}") // localhost:8080/contributions/filter/position/{position}
	public String getControllerFilterByPosition(@PathVariable(name = "position") EmployeePositions position, Model model) {
		try {
			ArrayList<ProjectContribution> contributionsFromDB = conFilter.filterByPosition(position);
			model.addAttribute("package", contributionsFromDB);
			model.addAttribute("info", "Contributions with position " + position);
			return "show-all-contributions";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
