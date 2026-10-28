package eu.virac.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import eu.virac.model.KPIRealisation;
import eu.virac.service.IKPIRealisationService;

@Controller
@RequestMapping("/kpireal/crud")
public class KPIRealisationCRUDnFilterController {

	@Autowired
	private IKPIRealisationService realService;

	@GetMapping("/all") // localhost:8080/kpireal/crud/all
	public String getControllerRetrievAllRealisations(Model model) {
		try {
			ArrayList<KPIRealisation> kpiRealFromDB = realService.findAllRealisations();
			model.addAttribute("package", kpiRealFromDB);
			return "show-all-kpi-realisations";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/kpireal/crud/all/1
	public String getControllerSelectRealisationById(@PathVariable(name = "id") int id, Model model) {
		try {
			KPIRealisation kpiRealFromDB = realService.findRealisationById(id);
			model.addAttribute("package", kpiRealFromDB);
			return "show-one-kpi-realisation";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}

	}

	@GetMapping("/remove/{id}") // localhost:8080/kpireal/crud/all/1
	public String getControllerRealisationForRemoval(@PathVariable(name = "id") int id, Model model) {
		try {
			realService.deleteRealisationById(id);
			ArrayList<KPIRealisation> kpiRealFromDB = realService.findAllRealisations();
			model.addAttribute("package", kpiRealFromDB);
			return "show-all-kpi-realisations";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/add") // localhost:8080/realkpi/crud/add
	public String getControllerForRealisationAdding(Model model) {
		try {

			model.addAttribute("realisation", new KPIRealisation());
			return "add-one-realisation";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/update/{id}") // localhost:8080/realkpi/crud/all/2
	public String retrieveUpdateuserById(@PathVariable(name = "id") int id, Model model) {
		try {
			KPIRealisation kpiRealFromDB = realService.findRealisationById(id);
			model.addAttribute("realisation", kpiRealFromDB);
			return "update-one-realisation";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

}
