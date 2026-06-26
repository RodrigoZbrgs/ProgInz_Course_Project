
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
import eu.virac.model.KPIWorkDescription;
import eu.virac.service.IWorkDescriptionService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/workdescriptions/crud")
public class WorkDescriptionCRUDController {

    @Autowired
    private IWorkDescriptionService workDescriptionService;

    @GetMapping("/list")//http://localhost:8080/workdescriptions/crud/list
    public String getControllerWorkDescriptionList(Model model) {
        try {
            ArrayList<KPIWorkDescription> workDescriptions = workDescriptionService.findAllWorkDescriptions();
            model.addAttribute("package", workDescriptions);
            return "show-all-wd";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }
    @GetMapping("/list/{id}")//http://localhost:8080/workdescriptions/crud/list/1
    public String getControllerSelectWorkDescriptionById(@PathVariable(name = "id") long id, Model model) {
        try {
            KPIWorkDescription workDescription = workDescriptionService.findWorkDescriptionById(id);
            model.addAttribute("package", workDescription);
            return "show-one-wd";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }
    @GetMapping("/remove/{id}") //http://localhost:8080/workdescriptions/crud/remove
    public String getControllerWorkDescriptionForRemoval(@PathVariable(name = "id") long id, Model model) {
        try {
            workDescriptionService.deleteWorkDescription(id);
            ArrayList<KPIWorkDescription> workDescriptions = workDescriptionService.findAllWorkDescriptions();
            model.addAttribute("package", workDescriptions);
            return "show-all-wd";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }
    @GetMapping("/add")//http://localhost:8080/workdescriptions/crud/add
    public String getControllerForWorkDescriptionAdding(Model model) {
        try {
            model.addAttribute("workDescription", new KPIWorkDescription());
            return "add-wd";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }
    @PostMapping("/add")
    public String postControllerForWorkDescriptionAdding(@Valid KPIWorkDescription workDescription, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-wd";
        } else {
            try {
                workDescriptionService.addWorkDescription(workDescription);
                return "redirect:/workdescriptions/crud/list";
            } catch (Exception e) {
                model.addAttribute("package", e.getMessage());
                return "error-page";
            }}
        }
        @GetMapping("/update/{id}")//http://localhost:8080/workdescriptions/crud/update/1
        public String retrieveUpdateWorkDescriptionById(@PathVariable(name = "id") long id, Model model) {
            try {
                KPIWorkDescription workDescription = workDescriptionService.findWorkDescriptionById(id);
                model.addAttribute("workDescription", workDescription);
                return "update-wd";
            } catch (Exception e) {
                model.addAttribute("package", e.getMessage());
                return "error-page";
            }
}
        @PostMapping("/update/{id}")
        public String postControllerForUpdateById(@PathVariable(name = "id") long id, 
                                                  KPIWorkDescription workDescription, 
                                                  Model model) {
            try {
                workDescriptionService.updateWorkDescription(id, workDescription.getDescription(), workDescription.getDate(), workDescription.getAmount());
                return "redirect:/workdescriptions/crud/list";
            } catch (Exception e) {
                model.addAttribute("package", e.getMessage());
                return "error-page";
            }
        }
    }
