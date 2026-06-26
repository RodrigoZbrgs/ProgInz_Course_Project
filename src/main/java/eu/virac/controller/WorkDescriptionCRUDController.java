package eu.virac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.virac.service.IWorkDescriptionService;

@Controller
@RequestMapping("/workdescriptions")
public class WorkDescriptionCRUDController {

    @Autowired
    private IWorkDescriptionService workDescriptionService;

    @GetMapping
    public String listWorkDescriptions(Model model) throws Exception {
        model.addAttribute("workDescriptions", workDescriptionService.findAllWorkDescriptions());
        return "workdescriptions/list";
    }
}
