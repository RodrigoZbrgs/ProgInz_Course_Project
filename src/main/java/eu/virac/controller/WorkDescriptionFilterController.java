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
import eu.virac.service.ITranslationService;
import eu.virac.service.IWorkDescriptionsFilter;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/workdescriptions/filter")
public class WorkDescriptionFilterController {
	@Autowired
	private IWorkDescriptionsFilter filterService;

	@Autowired
	private ITranslationService translationService;

	//filter by keyword
	@GetMapping("/description/{keyword}") //
	public String getWorkDescriptionsByDescription(@PathVariable(name = "keyword") String keyword,
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

	        ArrayList<KPIWorkDescription> workDescriptionFromDB = filterService.filterByDescription(keyword);

	        if (!"lv".equals(currentLanguage)) {
	            for (int i = 0; i < workDescriptionFromDB.size(); i++) {
	                KPIWorkDescription workDescription = workDescriptionFromDB.get(i);

	                String notTranslatedDescription = workDescription.getDescription();
	                String translatedDescription = translationService.translateText(notTranslatedDescription, currentLanguage);

	                workDescription.setDescription(translatedDescription);
	            }
	        }

	        model.addAttribute("package", workDescriptionFromDB);
	        model.addAttribute("info", "Work descriptions with description containing: " + keyword);
	        model.addAttribute("currentLanguage", currentLanguage);
	        return "show-all-wd";
	    } catch (Exception e) {
	        model.addAttribute("package", e.getMessage());
	        return "error-page";
	    }
	}
	@GetMapping("/date/{date}") //http://localhost:8080/workdescriptions/filter/date/2026-06-24
	public String filterByDate(@PathVariable(name = "date") String date,
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

	        LocalDate localDate = LocalDate.parse(date);
	        ArrayList<KPIWorkDescription> workDescriptionFromDB = filterService.filterByDate(localDate);

	        if (!"lv".equals(currentLanguage)) {
	            for (int i = 0; i < workDescriptionFromDB.size(); i++) {
	                KPIWorkDescription workDescription = workDescriptionFromDB.get(i);

	                String notTranslatedDescription = workDescription.getDescription();
	                String translatedDescription = translationService.translateText(notTranslatedDescription, currentLanguage);

	                workDescription.setDescription(translatedDescription);
	            }
	        }

	        model.addAttribute("package", workDescriptionFromDB);
	        model.addAttribute("info", "Filter by date: " + date);
	        model.addAttribute("currentLanguage", currentLanguage);
	        return "show-all-wd";
	    } catch (Exception e) {
	        model.addAttribute("package", e.getMessage());
	        return "error-page";
	    }
	}
	@GetMapping("/greater/{amount}")  //http://localhost:8080/workdescriptions/filter/greater/8
	public String filterByAmountGreaterThan(@PathVariable(name = "amount") int amount,
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

	        ArrayList<KPIWorkDescription> workDescriptionFromDB = filterService.filterByAmountGreaterThan(amount);

	        if (!"lv".equals(currentLanguage)) {
	            for (int i = 0; i < workDescriptionFromDB.size(); i++) {
	                KPIWorkDescription workDescription = workDescriptionFromDB.get(i);

	                String notTranslatedDescription = workDescription.getDescription();
	                String translatedDescription = translationService.translateText(notTranslatedDescription, currentLanguage);

	                workDescription.setDescription(translatedDescription);
	            }
	        }

	        model.addAttribute("package", workDescriptionFromDB);
	        model.addAttribute("info", "Filter by amount greater than: " + amount);
	        model.addAttribute("currentLanguage", currentLanguage);
	        return "show-all-wd";
	    } catch (Exception e) {
	        model.addAttribute("package", e.getMessage());
	        return "error-page";
	    }
	}
	@GetMapping("/lesser/{amount}") //http://localhost:8080/workdescriptions/filter/lesser/8
	public String filterByAmountLessThan(@PathVariable(name = "amount") int amount,
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

	        ArrayList<KPIWorkDescription> workDescriptionFromDB = filterService.filterAmountLessThan(amount);

	        if (!"lv".equals(currentLanguage)) {
	            for (int i = 0; i < workDescriptionFromDB.size(); i++) {
	                KPIWorkDescription workDescription = workDescriptionFromDB.get(i);

	                String notTranslatedDescription = workDescription.getDescription();
	                String translatedDescription = translationService.translateText(notTranslatedDescription, currentLanguage);

	                workDescription.setDescription(translatedDescription);
	            }
	        }

	        model.addAttribute("package", workDescriptionFromDB);
	        model.addAttribute("info", "Filter by amount lesser than: " + amount);
	        model.addAttribute("currentLanguage", currentLanguage);
	        return "show-all-wd";
	    } catch (Exception e) {
	        model.addAttribute("package", e.getMessage());
	        return "error-page";
	    }
	}
}
