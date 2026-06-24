package eu.virac;

import eu.virac.model.ProjectInformation;
import eu.virac.repo.IKPICategoriesRepo;
import eu.virac.repo.IKPIRealisation;
import eu.virac.repo.IKPISubcategoriesRepo;
import eu.virac.repo.IKPIWorkDescriptionRepo;
import eu.virac.repo.IKPI_StatusRepo;
import eu.virac.repo.IProjectContributionRepo;
import eu.virac.repo.IProjectInformationRepo;
import eu.virac.repo.IUsersRepo;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProgInzCourseProjectApplication {


	public static void main(String[] args) {
		SpringApplication.run(ProgInzCourseProjectApplication.class, args);
	}
	@Bean
	public CommandLineRunner saveDatainDB(IKPI_StatusRepo kpiStatusRepo, IKPICategoriesRepo kpiCatRepo, IKPIRealisation kpiRealisationRepo
			,IKPISubcategoriesRepo kpisubCatRepo, IKPIWorkDescriptionRepo kpiWorkDescRepo, IProjectInformationRepo prInfoRepo,
			IProjectContributionRepo prContrRepo, IUsersRepo usersRepo) {
		
	return new CommandLineRunner() {
		@Override
		public void run(String... args) throws Exception {
			
			//project contribution
			
			
			//project 
			ProjectInformation p1 = new ProjectInformation("P-123", "piemers1", LocalDate.of(2026, 6, 24), LocalDate.of(2027, 6, 24));
			ProjectInformation p2 = new ProjectInformation("P-124", "piemers2", LocalDate.of(2026, 6, 25), LocalDate.of(2027, 6, 25));
			ProjectInformation p3 = new ProjectInformation("P-125", "piemers3", LocalDate.of(2026, 6, 26), LocalDate.of(2027, 6, 26));
			prInfoRepo.saveAll(Arrays.asList(p1,p2,p3));
}
	};
}
}
