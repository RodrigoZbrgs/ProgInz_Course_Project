package eu.virac.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.virac.model.KPIWorkDescription;
import eu.virac.service.IUserWorkReportService;
import eu.virac.service.ITranslationService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reports")
public class UserWorkReportController {

    @Autowired
    private IUserWorkReportService reportService;

    @Autowired
    private ITranslationService translationService;

    @GetMapping("/user/{username}") // localhost:8080/reports/user/Rodrigo
    public String getUserWorkReport(@PathVariable(name = "username") String username,
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

            ArrayList<KPIWorkDescription> reportsFromDB = reportService.getUserWorkReport(username);

            if (!"en".equals(currentLanguage)) {
                for (int i = 0; i < reportsFromDB.size(); i++) {
                    KPIWorkDescription workDescription = reportsFromDB.get(i);

                    String notTranslatedDescription = workDescription.getDescription();
                    String translatedDescription = translationService.translateText(notTranslatedDescription, currentLanguage);

                    workDescription.setDescription(translatedDescription);

                    if (workDescription.getInformation() != null) {
                        String notTranslatedName = workDescription.getInformation().getProjectName();
                        String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

                        workDescription.getInformation().setProjectName(translatedName);
                    }
                }
            }

            model.addAttribute("package", reportsFromDB);
            model.addAttribute("username", username);
            model.addAttribute("count", reportsFromDB.size());
            model.addAttribute("currentLanguage", currentLanguage);
            return "show-user-work-report";
        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }
}
