package eu.virac.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.virac.model.KPIRealisation;
import eu.virac.service.IKPIRealisationService;
import eu.virac.service.ITranslationService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/kpireal/crud")
public class KPIRealisationCRUDnFilterController {

	@Autowired
	private IKPIRealisationService realService;

	@Autowired
	private ITranslationService translationService;

	@Autowired
	private CacheManager cacheManager;

	@GetMapping("/all")
	public String getControllerRetrievAllRealisations(
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

			Cache cache = cacheManager.getCache("KPI_Realisations");
			if (cache != null) {
				cache.clear();
			}

			ArrayList<KPIRealisation> kpiRealisationFromDB = realService.selectAll();

			if (!"en".equals(currentLanguage)) {
				for (int i = 0; i < kpiRealisationFromDB.size(); i++) {
					KPIRealisation kpiRealisation = kpiRealisationFromDB.get(i);

					String notTranslatedText = kpiRealisation.getTextvalue();
					String translatedText = translationService.translateText(notTranslatedText, currentLanguage);

					String notTranslatedComment = kpiRealisation.getComment();
					String translatedComment = translationService.translateText(notTranslatedComment, currentLanguage);

					kpiRealisation.setTextvalue(translatedText);
					kpiRealisation.setComment(translatedComment);
				}
			}

			model.addAttribute("package", kpiRealisationFromDB);
			model.addAttribute("currentLanguage", currentLanguage);
			// REMOVED redundant fetching and overwriting here

			return "show-all-kpi-realisations";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/all/{id}") 
	public String getControllerSelectRealisationById(@PathVariable(name = "id") int id,
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

			Cache cache = cacheManager.getCache("KPI_Realisations");
			if (cache != null) {
				cache.clear();
			}

			KPIRealisation kpiRealisationFromDB = realService.retrieve(id);

			if (!"en".equals(currentLanguage) && kpiRealisationFromDB != null) {
				String notTranslatedText = kpiRealisationFromDB.getTextvalue();
				String translatedText = translationService.translateText(notTranslatedText, currentLanguage);

				String notTranslatedComment = kpiRealisationFromDB.getComment();
				String translatedComment = translationService.translateText(notTranslatedComment, currentLanguage);

				kpiRealisationFromDB.setTextvalue(translatedText);
				kpiRealisationFromDB.setComment(translatedComment);
			}

			model.addAttribute("package", kpiRealisationFromDB);
			model.addAttribute("currentLanguage", currentLanguage);
			// REMOVED redundant fetching and overwriting here

			return "show-one-kpi-realisation";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/remove/{id}") 
	public String getControllerRealisationForRemoval(@PathVariable(name = "id") int id,
			HttpSession session,
			Model model) {
		try {
			realService.delete(id); // Keep this single deletion call

			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "en";
			}

			Cache cache = cacheManager.getCache("KPI_Realisations");
			if (cache != null) {
				cache.clear();
			}

			ArrayList<KPIRealisation> kpiRealisationFromDB = realService.selectAll();

			if (!"en".equals(currentLanguage)) {
				for (int i = 0; i < kpiRealisationFromDB.size(); i++) {
					KPIRealisation kpiRealisation = kpiRealisationFromDB.get(i);

					String notTranslatedText = kpiRealisation.getTextvalue();
					String translatedText = translationService.translateText(notTranslatedText, currentLanguage);

					String notTranslatedComment = kpiRealisation.getComment();
					String translatedComment = translationService.translateText(notTranslatedComment, currentLanguage);

					kpiRealisation.setTextvalue(translatedText);
					kpiRealisation.setComment(translatedComment);
				}
			}

			model.addAttribute("package", kpiRealisationFromDB);
			model.addAttribute("currentLanguage", currentLanguage);
			// REMOVED double-deletion and overwriting here

			return "show-all-kpi-realisations";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/add") 
	public String getControllerForRealisationAdding(HttpSession session, Model model) {
		try {
			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "en";
			}

			model.addAttribute("realisation", new KPIRealisation());
			model.addAttribute("currentLanguage", currentLanguage);
			return "add-one-realisation";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/update/{id}")
	public String retrieveUpdateuserById(@PathVariable(name = "id") int id,
			HttpSession session,
			Model model) {
		try { // ADDED MISSING TRY BLOCK HERE

			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "en";
			}

			Cache cache = cacheManager.getCache("KPI_Realisations");
			if (cache != null) {
				cache.clear();
			}

			KPIRealisation kpiRealisationFromDB = realService.retrieve(id);

			if (!"en".equals(currentLanguage) && kpiRealisationFromDB != null) {
				String notTranslatedText = kpiRealisationFromDB.getTextvalue();
				String translatedText = translationService.translateText(notTranslatedText, currentLanguage);

				String notTranslatedComment = kpiRealisationFromDB.getComment();
				String translatedComment = translationService.translateText(notTranslatedComment, currentLanguage);

				kpiRealisationFromDB.setTextvalue(translatedText);
				kpiRealisationFromDB.setComment(translatedComment);
			}

			model.addAttribute("realisation", kpiRealisationFromDB);
			model.addAttribute("currentLanguage", currentLanguage);
			// REMOVED redundant fetching and overwriting here

			return "update-one-realisation";

		} catch (Exception e) { // Catch block now has its matching try block
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}