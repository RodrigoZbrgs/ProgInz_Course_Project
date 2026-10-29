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
import org.springframework.web.bind.annotation.RequestParam;

import eu.virac.model.Users;
import eu.virac.service.ITranslationService;
import eu.virac.service.IUserService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/crud")
public class UserCRUDController {

	@Autowired
	private IUserService userService;

	@Autowired
	private ITranslationService translationService;

	@GetMapping("/all") // localhost:8080/user/crud/all
	public String getControllerRetrievAllUsers(
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

			ArrayList<Users> usersFromDB = userService.selectAllUser();

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
			model.addAttribute("currentLanguage", currentLanguage);
			return "show-all-users";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/user/crud/all/1
	public String getControllerSelectUsersById(
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

			Users usersFromDB = userService.selectUsersByUid(id);

			if (!"lv".equals(currentLanguage) && usersFromDB != null) {
				String notTranslatedName = usersFromDB.getName();
				String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

				String notTranslatedSurname = usersFromDB.getSurname();
				String translatedSurname = translationService.translateText(notTranslatedSurname, currentLanguage);

				usersFromDB.setName(translatedName);
				usersFromDB.setSurname(translatedSurname);
			}

			model.addAttribute("package", usersFromDB);
			model.addAttribute("currentLanguage", currentLanguage);
			return "show-one-user";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}

	}

	@GetMapping("/remove/{id}") // localhost:8080/user/crud/all/1
	public String getControllerUserForRemoval(@PathVariable(name = "id") int id,
			HttpSession session,
			Model model) {
		try {
			userService.deleteUserByUid(id);

			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "lv";
			}

			ArrayList<Users> usersFromDB = userService.selectAllUser();

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
			model.addAttribute("currentLanguage", currentLanguage);
			return "show-all-users";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/add") // localhost:8080/user/crud/add
	public String getControllerForUserAdding(HttpSession session, Model model) {
		try {
			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "lv";
			}

			model.addAttribute("user", new Users());
			model.addAttribute("currentLanguage", currentLanguage);
			return "add-one-user";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/update/{id}") // localhost:8080/user/crud/all/2
	public String retrieveUpdateuserById(@PathVariable(name = "id") int id,
			HttpSession session,
			Model model) {
		try {
			String currentLanguage = (String) session.getAttribute("currentLanguage");
			if (currentLanguage == null) {
				currentLanguage = "lv";
			}

			Users usersFromDB = userService.selectUsersByUid(id);

			if (!"lv".equals(currentLanguage) && usersFromDB != null) {
				String notTranslatedName = usersFromDB.getName();
				String translatedName = translationService.translateText(notTranslatedName, currentLanguage);

				String notTranslatedSurname = usersFromDB.getSurname();
				String translatedSurname = translationService.translateText(notTranslatedSurname, currentLanguage);

				usersFromDB.setName(translatedName);
				usersFromDB.setSurname(translatedSurname);
			}

			model.addAttribute("user", usersFromDB);
			model.addAttribute("currentLanguage", currentLanguage);
			return "update-one-user";

		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
