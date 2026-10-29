package eu.virac.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eu.virac.model.Users;
import eu.virac.service.ITranslationService;
import eu.virac.service.IUserFilter;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/filter")
public class UserFilterController {
    
    @Autowired
    private IUserFilter Ufilterservice;
    
    @Autowired
    private ITranslationService translationService;

    @GetMapping("/name/{letter}") // localhost:8080/user/filter/name/M
    public String getUsersByFirstLetter(@PathVariable(name = "letter") String letter,
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

            ArrayList<Users> usersFromDB = Ufilterservice.filterByStartingWith(letter);

            if (!"lv".equals(currentLanguage)) {
                for (int i = 0; i < usersFromDB.size(); i++) {
                    Users user = usersFromDB.get(i);

                    String notTranslatedName = user.getName();
                    String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

                    String notTranslatedSurname = user.getSurname();
                    String translatedSurname = translationService.translateText(notTranslatedSurname, currentLanguage);

                    user.setName(translatedName);
                    user.setSurname(translatedSurname);
                }
            }

            model.addAttribute("package", usersFromDB);
            model.addAttribute("info", "Users with name starting letter: " + letter);
            model.addAttribute("currentLanguage", currentLanguage);
            return "show-all-users";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }

    // FILTER BY EMAIL
    @GetMapping("/email/{email}") // localhost:8080/user/filter/email/gmail
    public String getUsersByEmail(@PathVariable(name = "keyword") String Emailkeyword,
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

            ArrayList<Users> usersFromDB = Ufilterservice.filterByEmail(Emailkeyword);

            if (!"lv".equals(currentLanguage)) {
                for (int i = 0; i < usersFromDB.size(); i++) {
                    Users user = usersFromDB.get(i);

                    String notTranslatedName = user.getName();
                    String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

                    String notTranslatedSurname = user.getSurname();
                    String translatedSurname = translationService.translateText(notTranslatedSurname, currentLanguage);

                    user.setName(translatedName);
                    user.setSurname(translatedSurname);
                }
            }

            model.addAttribute("package", usersFromDB);
            model.addAttribute("info", "Users with email: " + Emailkeyword);
            model.addAttribute("currentLanguage", currentLanguage);
            return "show-all-users";

        } catch (Exception e) {
            model.addAttribute("package", e.getMessage());
            return "error-page";
        }
    }
}