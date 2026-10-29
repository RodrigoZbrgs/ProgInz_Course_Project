package eu.virac.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.virac.model.KPIWorkDescription;
import eu.virac.model.Users;
import eu.virac.repo.IUsersRepo;
import eu.virac.service.IProjectService;
import eu.virac.service.IDepartmentService;
import eu.virac.service.ITranslationService;
import eu.virac.service.IUserService;
import eu.virac.service.IWorkDescriptionService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/workdescriptions/crud")
public class WorkDescriptionCRUDController {

    @Autowired
    private IWorkDescriptionService workDescriptionService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IProjectService projectService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private ITranslationService translationService;

    @GetMapping("/list")//http://localhost:8080/workdescriptions/crud/list
    public String getControllerWorkDescriptionList(
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

            ArrayList<KPIWorkDescription> workDescriptions = workDescriptionService.findAllWorkDescriptions();

            if (!"en".equals(currentLanguage)) {
                for (int i = 0; i < workDescriptions.size(); i++) {
                    KPIWorkDescription workDescription = workDescriptions.get(i);

                    String notTranslatedDescription = workDescription.getDescription();
                    String translatedDescription = translationService.translateText(notTranslatedDescription, currentLanguage);

                    workDescription.setDescription(translatedDescription);
                }
            }

            model.addAttribute("package", workDescriptions);
            model.addAttribute("currentLanguage", currentLanguage);
            return "show-all-wd";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/list/{id}")//http://localhost:8080/workdescriptions/crud/list/1
    public String getControllerSelectWorkDescriptionById(@PathVariable(name = "id") long id,
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

            KPIWorkDescription workDescription = workDescriptionService.findWorkDescriptionById(id);

            if (!"en".equals(currentLanguage) && workDescription != null) {
                String notTranslatedDescription = workDescription.getDescription();
                String translatedDescription = translationService.translateText(notTranslatedDescription, currentLanguage);

                workDescription.setDescription(translatedDescription);
            }

            model.addAttribute("package", workDescription);
            model.addAttribute("currentLanguage", currentLanguage);
            return "show-one-wd";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/remove/{id}") //http://localhost:8080/workdescriptions/crud/remove
    public String getControllerWorkDescriptionForRemoval(@PathVariable(name = "id") long id,
            HttpSession session,
            Model model) {
        try {
            workDescriptionService.deleteWorkDescription(id);

            String currentLanguage = (String) session.getAttribute("currentLanguage");
            if (currentLanguage == null) {
                currentLanguage = "en";
            }

            ArrayList<KPIWorkDescription> workDescriptions = workDescriptionService.findAllWorkDescriptions();

            if (!"en".equals(currentLanguage)) {
                for (int i = 0; i < workDescriptions.size(); i++) {
                    KPIWorkDescription workDescription = workDescriptions.get(i);

                    String notTranslatedDescription = workDescription.getDescription();
                    String translatedDescription = translationService.translateText(notTranslatedDescription, currentLanguage);

                    workDescription.setDescription(translatedDescription);
                }
            }

            model.addAttribute("package", workDescriptions);
            model.addAttribute("currentLanguage", currentLanguage);
            return "show-all-wd";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/add")//http://localhost:8080/workdescriptions/crud/add
    public String getControllerForWorkDescriptionAdding(HttpSession session, Model model) {
        try {
            String currentLanguage = (String) session.getAttribute("currentLanguage");
            if (currentLanguage == null) {
                currentLanguage = "en";
            }

            model.addAttribute("workDescription", new KPIWorkDescription());
            model.addAttribute("users", userService.selectAllUser());
            model.addAttribute("projects", projectService.selectAllProjects());
            model.addAttribute("departments", departmentService.selectAllDepartments());
            model.addAttribute("currentLanguage", currentLanguage);
            return "add-wd";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/add")
    public String postControllerForWorkDescriptionAdding(@Valid KPIWorkDescription workDescription, BindingResult result, Model model) throws Exception {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors()); 
            model.addAttribute("users", userService.selectAllUser());
            model.addAttribute("projects", projectService.selectAllProjects());
            model.addAttribute("departments", departmentService.selectAllDepartments());
            return "add-wd";
        } else {
            try {
                workDescriptionService.addWorkDescription(workDescription);
                return "redirect:/workdescriptions/crud/list";
            } catch (Exception e) {
                model.addAttribute("package", e.getMessage());
                return "error-page";
            }
        }
    }

    @GetMapping("/update/{id}")//http://localhost:8080/workdescriptions/crud/update/1
    public String retrieveUpdateWorkDescriptionById(@PathVariable(name = "id") long id,
            HttpSession session,
            Model model) {
        try {
            String currentLanguage = (String) session.getAttribute("currentLanguage");
            if (currentLanguage == null) {
                currentLanguage = "en";
            }

            KPIWorkDescription workDescription = workDescriptionService.findWorkDescriptionById(id);

            if (!"en".equals(currentLanguage) && workDescription != null) {
                String notTranslatedDescription = workDescription.getDescription();
                String translatedDescription = translationService.translateText(notTranslatedDescription, currentLanguage);

                workDescription.setDescription(translatedDescription);
            }

            model.addAttribute("workDescription", workDescription);
            model.addAttribute("users", userService.selectAllUser());
            model.addAttribute("projects", projectService.selectAllProjects());
            model.addAttribute("departments", departmentService.selectAllDepartments());
            model.addAttribute("currentLanguage", currentLanguage);
            return "update-wd";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @PostMapping("/update/{id}")
    public String postControllerForUpdateById(@PathVariable(name = "id") long id, KPIWorkDescription workDescription,
            Model model) {
        try {
            workDescriptionService.updateWorkDescription(id, workDescription.getDescription(),
                    workDescription.getDate(), workDescription.getPriority(), workDescription.getUser(), workDescription.getInformation(),
                    workDescription.getDepartment());

            return "redirect:/workdescriptions/crud/list";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/paginatorTest") // http://localhost:8080/workdescriptions/paginatorTest
    public String findAllWorkDescriptionsPaginated(
            @RequestParam(defaultValue = "0") int page,
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

            Pageable pageWithXElements = PageRequest.of(page, 5);
            Page<KPIWorkDescription> pageData = workDescriptionService.findAllWorkDescriptionsPaginated(pageWithXElements);

            if (!"en".equals(currentLanguage)) {
                for (int i = 0; i < pageData.getContent().size(); i++) {
                    KPIWorkDescription workDescription = pageData.getContent().get(i);

                    String notTranslatedDescription = workDescription.getDescription();
                    String translatedDescription = translationService.translateText(notTranslatedDescription, currentLanguage);

                    workDescription.setDescription(translatedDescription);
                }
            }

            model.addAttribute("package", pageData);
            model.addAttribute("currentLanguage", currentLanguage);
            return "show-all-wd-Paginator";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }
}