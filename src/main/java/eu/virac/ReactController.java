package eu.virac;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<ProjectInformation> retrieveAllProjects() throws Exception {
	    return projService.selectAllProjects(); 
	}
	
	@GetMapping("/projects/{id}")
	public ProjectInformation retrieveProjectById(@PathVariable(name = "id") int id) throws Exception {
		return projService.selectProjectById(id);
	}
	
	@PostMapping("/projects")
	public ProjectInformation createProject(@RequestBody ProjectInformation project) throws Exception {
		return projService.insertNewProject(project);
	}
	
	@DeleteMapping("/projects/{id}")
	public void deleteProject(@PathVariable(name = "id") int id) throws Exception {
		projService.deleteProjectById(id);
	}
	
	@PutMapping("/projects/{id}")
	public void updateProject(@PathVariable(name = "id") int id, @RequestBody ProjectInformation project) {
		
	}
	
}
