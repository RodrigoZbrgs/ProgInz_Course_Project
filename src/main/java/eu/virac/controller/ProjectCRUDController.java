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

import eu.virac.model.ProjectInformation;
import eu.virac.service.IProjectService;
import jakarta.validation.Valid;




@Controller
@RequestMapping("/project/crud")
public class ProjectCRUDController {

	@Autowired
	private IProjectService projService;
	
	@GetMapping("/all") // localhost:8080/project/crud/all
	public String getControllerRetrievAllProjects(Model model) {
		try {
			ArrayList<ProjectInformation> projectsFromDB = projService.selectAllProjects();
			model.addAttribute("package", projectsFromDB);
			return "show-all-projects";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/all/{id}")
	public String getControllerSelectProjectById(@PathVariable(name = "id") int id, Model model) {
		try {
			ProjectInformation projectsFromDB = projService.selectProjectById(id);
			model.addAttribute("package", projectsFromDB);
			return "show-one-project";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}

	}
	
	@GetMapping("/remove/{id}")
	public String getControllerProjectForRemoval(@PathVariable(name = "id") int id, Model model) {
		try {
			projService.deleteProjectById(id);
			ArrayList<ProjectInformation> projectsFromDB = projService.selectAllProjects();
			model.addAttribute("package", projectsFromDB);
			return "show-all-projects";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	
	@GetMapping("/add")
	public String getControllerForProjectAdding(Model model) {
		try {

			model.addAttribute("project", new ProjectInformation());
			return "add-one-project";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/add")
	public String postControllerForProjectAdding(@Valid ProjectInformation project, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-one-project";
		} else {
			try {

				projService.insertNewProject(project);
				return "redirect:/project/crud/all";

			} catch (Exception e) {
				model.addAttribute("package", e.getMessage());
				return "error-page";
			}
		}
	}
	
	@GetMapping("/update/{id}")
	public String retrieveUpdateProjectById(@PathVariable(name = "id") int id, Model model) {
		try {
			ProjectInformation projectsFromDB = projService.selectProjectById(id);
			model.addAttribute("project", projectsFromDB);
			return "update-one-project";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update/{id}")
	public String postControllerForUpdateById(@PathVariable(name = "id") int id, ProjectInformation project, Model model) {
		try {
			projService.updateByidpi(id, project.getProjectNumber(), project.getProjectName(), project.getStartingDate(), project.getEndingDate());
			return "redirect:/project/crud/all/" + id;
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
}
