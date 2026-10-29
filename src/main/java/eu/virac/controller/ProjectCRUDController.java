package eu.virac.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import eu.virac.model.ProjectInformation;
import eu.virac.service.IProjectService;
import eu.virac.service.ITranslationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/project/crud")
public class ProjectCRUDController {

    @Autowired
    private IProjectService projService;

    @Autowired
    private ITranslationService translationService;

    @GetMapping("/all") // localhost:8080/project/crud/all
    public String getControllerRetrievAllProjects(
            @RequestParam(name = "language", required = false) String language, 
            HttpSession session, 
            Model model) {
        try {
            if (language != null && !language.isEmpty()) {
                session.setAttribute("currentLanguage", language);
            }

            String currentLanguage = (String) session.getAttribute("currentLanguage");
            if (currentLanguage == null) {
                currentLanguage = "lv";
            }

            ArrayList<ProjectInformation> projectsArray = projService.selectAllProjects();

            if (!"lv".equals(currentLanguage)) {
                for (int i = 0; i < projectsArray.size(); i++) {
                    ProjectInformation proj = projectsArray.get(i);
                    
                    String notTranslatedName = proj.getProjectName();
                    String translatedName = translationService.translateText(notTranslatedName, currentLanguage);
                    
                    proj.setProjectName(translatedName);
                }
            }

            model.addAttribute("package", projectsArray);
            model.addAttribute("currentLanguage", currentLanguage);
            
            return "show-all-projects";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/all/{id}")
    public String getControllerSelectProjectById(
            @PathVariable(name = "id") int id, 
            @RequestParam(name = "language", required = false) String language,
            HttpSession session,
            Model model) {
        try {
            if (language != null && !language.isEmpty()) {
                session.setAttribute("currentLanguage", language);
            }

            String currentLanguage = (String) session.getAttribute("currentLanguage");
            if (currentLanguage == null) {
                currentLanguage = "lv";
            }

            ProjectInformation projects = projService.selectProjectById(id);

            if (!"lv".equals(currentLanguage) && projects != null) {
                String notTranslatedName = projects.getProjectName();
                String translatedName = translationService.translateText(notTranslatedName, currentLanguage);
                projects.setProjectName(translatedName);
            }

            model.addAttribute("package", projects);
            model.addAttribute("currentLanguage", currentLanguage);
            return "show-one-project";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/remove/{id}")
    public String getControllerProjectForRemoval(
            @PathVariable(name = "id") int id, 
            HttpSession session,
            Model model) {
        try {
            projService.deleteProjectById(id);

            String currentLanguage = (String) session.getAttribute("currentLanguage");
            if (currentLanguage == null) {
                currentLanguage = "lv";
            }

            ArrayList<ProjectInformation> projectsArray = projService.selectAllProjects();

            if (!"lv".equals(currentLanguage)) {
                for (int i = 0; i < projectsArray.size(); i++) {
                    ProjectInformation proj = projectsArray.get(i);
                    
                    String notTranslatedName = proj.getProjectName();
                    String translatedName = translationService.translateText(notTranslatedName, currentLanguage);
                    
                    proj.setProjectName(translatedName);
                }
            }

            model.addAttribute("package", projectsArray);
            model.addAttribute("currentLanguage", currentLanguage);
            return "show-all-projects";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/add")
    public String getControllerForProjectAdding(HttpSession session, Model model) {
        try {
            String currentLanguage = (String) session.getAttribute("currentLanguage");
            if (currentLanguage == null) {
                currentLanguage = "lv";
            }

            model.addAttribute("project", new ProjectInformation());
            model.addAttribute("currentLanguage", currentLanguage);
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
    public String retrieveUpdateProjectById(
            @PathVariable(name = "id") int id, 
            HttpSession session,
            Model model) {
        try {
            String currentLanguage = (String) session.getAttribute("currentLanguage");
            if (currentLanguage == null) {
                currentLanguage = "lv";
            }

            ProjectInformation projects = projService.selectProjectById(id);

            if (!"lv".equals(currentLanguage) && projects != null) {
                String notTranslatedName = projects.getProjectName();
                String translatedName = translationService.translateText(notTranslatedName, currentLanguage);
                projects.setProjectName(translatedName);
            }

            model.addAttribute("project", projects);
            model.addAttribute("currentLanguage", currentLanguage);
            return "update-one-project";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String postControllerForUpdateById(@PathVariable(name = "id") int id, ProjectInformation project,
            Model model) {
        try {
            projService.updateByidpi(id, project.getProjectNumber(), project.getProjectName(),
                    project.getStartingDate(), project.getEndingDate());
            return "redirect:/project/crud/all/" + id;
            
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

}