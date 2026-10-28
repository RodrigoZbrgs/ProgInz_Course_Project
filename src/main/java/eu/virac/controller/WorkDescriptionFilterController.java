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

import eu.virac.model.KPIWorkDescription;
import eu.virac.model.enums.Priority;
import eu.virac.service.IWorkDescriptionsFilter;
@Controller
@RequestMapping("/workdescriptions/filter")
public class WorkDescriptionFilterController {
	@Autowired
	private IWorkDescriptionsFilter filterService;
	//filter by keyword
	@GetMapping("/description/{keyword}") //
	public String getWorkDescriptionsByDescription(@PathVariable(name = "keyword") String keyword, Model model) {
	    try {
	        ArrayList<KPIWorkDescription> WdFromDB = filterService.filterByDescription(keyword);
	        model.addAttribute("package", WdFromDB);
	        model.addAttribute("info", "Work descriptions with description containing: " + keyword);
	        return "show-all-wd";
	    } catch (Exception e) {
	        model.addAttribute("package", e.getMessage());
	        return "error-page";
	    }
	}
	@GetMapping("/date/{date}") //http://localhost:8080/workdescriptions/filter/date/2026-06-24
	public String filterByDate(@PathVariable(name = "date") String date, Model model) {
	    try {
	        LocalDate localDate = LocalDate.parse(date);
	        ArrayList<KPIWorkDescription> WdFromDB = filterService.filterByDate(localDate);
	        model.addAttribute("package", WdFromDB);
	        model.addAttribute("info", "Filter by date: " + date);
	        return "show-all-wd";
	    } catch (Exception e) {
	        model.addAttribute("package", e.getMessage());
	        return "error-page";
	    }
	}
	@GetMapping("/priority/{priority}")  //http://localhost:8080/workdescriptions/filter/greater/8
	public String filterByPriority(@PathVariable(name = "priority") Priority priority, Model model) {
	    try {
	        ArrayList<KPIWorkDescription> WdFromDB = filterService.filterByPriority(priority);
	        model.addAttribute("package", WdFromDB);
	        model.addAttribute("info", "Filter by priority: " + priority);
	        return "show-all-wd";
	    } catch (Exception e) {
	        model.addAttribute("package", e.getMessage());
	        return "error-page";
	    }
	}
	
//	@GetMapping("/lesser/{amount}") //http://localhost:8080/workdescriptions/filter/lesser/8
//	public String filterByAmountLessThan(@PathVariable(name = "amount") int amount, Model model) {
//	    try {
//	        ArrayList<KPIWorkDescription> WdFromDB = filterService.filterAmountLessThan(amount);
//	        model.addAttribute("package", WdFromDB);
//	        model.addAttribute("info", "Filter by amount lesser than: " + amount);
//	        return "show-all-wd";
//	    } catch (Exception e) {
//	        model.addAttribute("package", e.getMessage());
//	        return "error-page";
//	    }
//	}
}
