package eu.virac.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.virac.model.ProjectInformation;
import eu.virac.service.IProjectFIlter;


@Controller
@RequestMapping("/project/filter")
public class ProjectFilterController {
 
	@Autowired
	private IProjectFIlter filterService;
	
	//starting date
	@GetMapping("/startingdate/{startingdate}")//localhost:8080/project/filter/startingdate/{startingdate}
	public String getControllerFilterByStartingDate(@PathVariable(name = "startingdate") LocalDate StartingDate, Model model) {
		try {
			ArrayList<ProjectInformation> ProjectsFromDB = filterService.filterByStartingDate(StartingDate);
			model.addAttribute("package", ProjectsFromDB);
			model.addAttribute("info", "Project with starting date " + StartingDate);
			return "show-all-projects";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	//ending date
	@GetMapping("/endingdate/{endingdate}")//localhost:8080//project/filter/endingdate/{endingdate}
	public String getControllerFilterByEndingDate(@PathVariable(name = "endingdate") LocalDate endingDate, Model model) {
		try {
			ArrayList<ProjectInformation> ProjectsFromDB = filterService.filterByEndingDate(endingDate);
			model.addAttribute("package", ProjectsFromDB);
			model.addAttribute("info", "Project with ending date " + endingDate);
			return "show-all-projects";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	//activity
	@GetMapping("/activity/{activity}")//localhost:8080//project/filter/activity/{activity}
	public String getControllerFilterByActivity(@PathVariable(name = "activity") Boolean activity, Model model) {
		try {
			ArrayList<ProjectInformation> ProjectsFromDB = filterService.filterByActivity(activity);
			model.addAttribute("package", ProjectsFromDB);
			model.addAttribute("info", "Project with status " + activity);
			return "show-all-projects";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	//keyword
	@GetMapping("/keyword/{keyword}")//localhost:8080//project/filter/keyword/{keyword}
	public String getControllerFilterByKeyword(@PathVariable(name = "keyword") String keyword, Model model) {
		try {
			ArrayList<ProjectInformation> ProjectsFromDB = filterService.filterByKeyWord(keyword);
			model.addAttribute("package", ProjectsFromDB);
			model.addAttribute("info", "Projekts ar atslēgas vārdu: " + keyword);
			return "show-all-projects";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
