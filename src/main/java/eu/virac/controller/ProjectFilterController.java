package eu.virac.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.virac.model.ProjectInformation;
import eu.virac.service.IProjectFIlter;
import eu.virac.service.ITranslationService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/project/filter")
public class ProjectFilterController {

	@Autowired
	private IProjectFIlter filterService;

	@Autowired
	private ITranslationService translationService;

	// starting date
	@GetMapping("/startingdate/{startingdate}") // localhost:8080/project/filter/startingdate/{startingdate}
	public String getControllerFilterByStartingDate(@PathVariable(name = "startingdate") LocalDate StartingDate,
			@RequestParam(name = "language", required = false) String language,
			HttpSession session,
			Model model) {
		try {
			if (language != null && !language.isEmpty()) {
				session.setAttribute("currentLanguage", language);
			}

			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "en";
			}

			ArrayList<ProjectInformation> ProjectsFromDB = filterService.filterByStartingDate(StartingDate);

			if (!"en".equals(currentLanguage)) {
				for (int i = 0; i < ProjectsFromDB.size(); i++) {
					ProjectInformation project = ProjectsFromDB.get(i);

					String notTranslatedName = project.getProjectName();
					String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

					project.setProjectName(translatedName);
				}
			}

			model.addAttribute("package", ProjectsFromDB);
			model.addAttribute("info", "Project with starting date " + StartingDate);
			model.addAttribute("currentLanguage", currentLanguage);
			return "show-all-projects";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	// ending date
	@GetMapping("/endingdate/{endingdate}") // localhost:8080//project/filter/endingdate/{endingdate}
	public String getControllerFilterByEndingDate(@PathVariable(name = "endingdate") LocalDate endingDate,
			@RequestParam(name = "language", required = false) String language,
			HttpSession session,
			Model model) {
		try {
			if (language != null && !language.isEmpty()) {
				session.setAttribute("currentLanguage", language);
			}

			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "en";
			}

			ArrayList<ProjectInformation> ProjectsFromDB = filterService.filterByEndingDate(endingDate);

			if (!"en".equals(currentLanguage)) {
				for (int i = 0; i < ProjectsFromDB.size(); i++) {
					ProjectInformation project = ProjectsFromDB.get(i);

					String notTranslatedName = project.getProjectName();
					String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

					project.setProjectName(translatedName);
				}
			}

			model.addAttribute("package", ProjectsFromDB);
			model.addAttribute("info", "Project with ending date " + endingDate);
			model.addAttribute("currentLanguage", currentLanguage);
			return "show-all-projects";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	// activity
	@GetMapping("/activity/{activity}") // localhost:8080//project/filter/activity/{activity}
	public String getControllerFilterByActivity(@PathVariable(name = "activity") Boolean activity,
			@RequestParam(name = "language", required = false) String language,
			HttpSession session,
			Model model) {
		try {
			if (language != null && !language.isEmpty()) {
				session.setAttribute("currentLanguage", language);
			}

			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "en";
			}

			ArrayList<ProjectInformation> ProjectsFromDB = filterService.filterByActivity(activity);

			if (!"en".equals(currentLanguage)) {
				for (int i = 0; i < ProjectsFromDB.size(); i++) {
					ProjectInformation project = ProjectsFromDB.get(i);

					String notTranslatedName = project.getProjectName();
					String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

					project.setProjectName(translatedName);
				}
			}

			model.addAttribute("package", ProjectsFromDB);
			model.addAttribute("info", "Project with status " + activity);
			model.addAttribute("currentLanguage", currentLanguage);
			return "show-all-projects";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	// keyword
	@GetMapping("/keyword/{keyword}") // localhost:8080//project/filter/keyword/{keyword}
	public String getControllerFilterByKeyword(@PathVariable(name = "keyword") String keyword,
			@RequestParam(name = "language", required = false) String language,
			HttpSession session,
			Model model) {
		try {
			if (language != null && !language.isEmpty()) {
				session.setAttribute("currentLanguage", language);
			}

			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "en";
			}

			ArrayList<ProjectInformation> ProjectsFromDB = filterService.filterByKeyWord(keyword);

			if (!"en".equals(currentLanguage)) {
				for (int i = 0; i < ProjectsFromDB.size(); i++) {
					ProjectInformation project = ProjectsFromDB.get(i);

					String notTranslatedName = project.getProjectName();
					String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

					project.setProjectName(translatedName);
				}
			}

			model.addAttribute("package", ProjectsFromDB);
			model.addAttribute("info", "Projekts ar atslēgas vārdu: " + keyword);
			model.addAttribute("currentLanguage", currentLanguage);
			return "show-all-projects";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	//Project Number
	@GetMapping("/projectnumber/{projectnumber}")
	public String getControllerFilterByProjectnumber(@PathVariable(name = "projectnumber") String projectNumber,
			@RequestParam(name = "language", required = false) String language,
			HttpSession session,
			Model model) {
		try {
			if (language != null && !language.isEmpty()) {
				session.setAttribute("currentLanguage", language);
			}

			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "en";
			}

			ArrayList<ProjectInformation> ProjectsFromDB = filterService.filterByProjectNumber(projectNumber);

			if (!"en".equals(currentLanguage)) {
				for (int i = 0; i < ProjectsFromDB.size(); i++) {
					ProjectInformation project = ProjectsFromDB.get(i);

					String notTranslatedName = project.getProjectName();
					String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

					project.setProjectName(translatedName);
				}
			}

			model.addAttribute("package", ProjectsFromDB);
			model.addAttribute("info", "Projekts ar projekta numuru: " + projectNumber);
			model.addAttribute("currentLanguage", currentLanguage);
			return "show-all-projects";
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	//Specifc date
	@GetMapping("/projectdate/{date}")
	public String getControllerFilterByDate(@PathVariable(name = "date") LocalDate date,
			@RequestParam(name = "language", required = false) String language,
			HttpSession session,
			Model model) {
		try {
			if (language != null && !language.isEmpty()) {
				session.setAttribute("currentLanguage", language);
			}

			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "en";
			}

			ArrayList<ProjectInformation> ProjectsFromDB = filterService.filterBySpecificDate(date);

			if (!"en".equals(currentLanguage)) {
				for (int i = 0; i < ProjectsFromDB.size(); i++) {
					ProjectInformation project = ProjectsFromDB.get(i);

					String notTranslatedName = project.getProjectName();
					String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

					project.setProjectName(translatedName);
				}
			}

			model.addAttribute("package", ProjectsFromDB);
			model.addAttribute("info", "Projekts ar datumu: " + date);
			model.addAttribute("currentLanguage", currentLanguage);
			return "show-all-projects";
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
}
