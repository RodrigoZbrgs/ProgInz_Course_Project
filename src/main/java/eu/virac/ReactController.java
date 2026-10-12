package eu.virac;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.virac.model.ProjectInformation;
import eu.virac.service.IKPIRealisationService;
import eu.virac.service.IProjectContributionService;
import eu.virac.service.IProjectService;
import eu.virac.service.IUserService;
import eu.virac.service.IWorkDescriptionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ReactController {

	@Autowired
	private IKPIRealisationService kpiRealService;

	@Autowired
	private IProjectContributionService projectContrService;

	@Autowired
	private IProjectService projService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IWorkDescriptionService workDescriptionService;

	@GetMapping("/projects")
	public ResponseEntity<?> retrieveAllProjects() {
		try {
			return new ResponseEntity<ArrayList<ProjectInformation>>(projService.selectAllProjects(),
					HttpStatusCode.valueOf(200));

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}

	}

	@GetMapping("/projects/{id}")
	public ResponseEntity<?> retrieveProjectById(@PathVariable long id) {
		try {
			return new ResponseEntity<ProjectInformation>(projService.selectProjectById(id),
					HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}

	@PostMapping("/projects/add")
	public ResponseEntity<?> createProject(@Valid @RequestBody ProjectInformation project, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Integer>(result.getErrorCount(), HttpStatusCode.valueOf(409));
		}

		try {
			projService.insertNewProject(project);
			return new ResponseEntity<ArrayList<ProjectInformation>>(projService.selectAllProjects(),
					HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}

	@DeleteMapping("/projects/delete/{id}")
	public ResponseEntity<?> deleteProjectById(@PathVariable(name = "id") int id) {
		try {
			projService.deleteProjectById(id);
			return new ResponseEntity<ArrayList<ProjectInformation>>(projService.selectAllProjects(),
					HttpStatusCode.valueOf(200));
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}
	}

	@PutMapping("/projects/update/{id}")
	public ResponseEntity<?> updateProjectById(@PathVariable(name = "id") long id,
			@RequestBody ProjectInformation project) {
		try {
			projService.updateByidpi(id, project.getProjectNumber(), project.getProjectName(),
					project.getStartingDate(), project.getEndingDate());
			return new ResponseEntity<ArrayList<ProjectInformation>>(projService.selectAllProjects(),
					HttpStatusCode.valueOf(200));
		}

		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(409));
		}

	}

}
