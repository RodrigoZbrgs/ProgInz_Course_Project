package eu.virac;

import eu.virac.model.KPIRealisation;
import eu.virac.model.KPIWorkDescription;
import eu.virac.model.ProjectContribution;
import eu.virac.model.ProjectInformation;
import eu.virac.model.Users;
import eu.virac.model.enums.EmployeePositions;
import eu.virac.repo.IKPICategoriesRepo;
import eu.virac.repo.IKPIRealisationRepo;
import eu.virac.repo.IKPIRealisationRepo;
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
	public CommandLineRunner saveDatainDB(IKPI_StatusRepo kpiStatusRepo, IKPICategoriesRepo kpiCatRepo,
			IKPIRealisationRepo kpiRealisationRepo, IKPISubcategoriesRepo kpisubCatRepo,
			IKPIWorkDescriptionRepo kpiWorkDescRepo, IProjectInformationRepo prInfoRepo,
			IProjectContributionRepo prContrRepo, IUsersRepo usersRepo) {

		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				if (prInfoRepo.count() == 0) {
					// project
					ProjectInformation p1 = new ProjectInformation(true, "P123", "ProjectAlpha",
							LocalDate.of(2026, 6, 24), LocalDate.of(2027, 6, 24));
					ProjectInformation p2 = new ProjectInformation(false, "P124", "ProjectBravo",
							LocalDate.of(2026, 6, 25), LocalDate.of(2027, 6, 25));
					ProjectInformation p3 = new ProjectInformation(true, "P125", "ProjectEcho",
							LocalDate.of(2026, 6, 26), LocalDate.of(2027, 6, 26));

					prInfoRepo.saveAll(Arrays.asList(p1, p2, p3));

					Users u1 = new Users("Rodrigo", "Zandbergs", "rodrigo.zandbergs@gmail.com", "********");
					Users u2 = new Users("Maris", "Maize", "maris.maize@inbox.com", "rootadmin");
					Users u3 = new Users("Marcis", "Rupmaize", "marcis.rupmaize@gmail.com", "adminroot");
					usersRepo.saveAll(Arrays.asList(u1, u2, u3));

					KPIRealisation kpir1 = new KPIRealisation(LocalDate.of(2026, 3, 24), "Fixed up the telescope",
                            "wasnt easy :(");
                    KPIRealisation kpir2 = new KPIRealisation(LocalDate.of(2026, 3, 24), "Finally aligned the primary mirrors",
                            "took three coffees and a existential crisis");
                    KPIRealisation kpir3 = new KPIRealisation(LocalDate.of(2026, 3, 24), "Calibrated the spectrograph",
                            "still don't think it's 100% right but I'm leaving it");
                    kpiRealisationRepo.saveAll(Arrays.asList(kpir1, kpir2, kpir3));
					
					KPIWorkDescription wd1 = new KPIWorkDescription(LocalDate.of(2026, 6, 24), 10, "Debugging my toaster");
	                KPIWorkDescription wd2 = new KPIWorkDescription(LocalDate.of(2026, 6, 25), 2, "Writting documentations");
	                KPIWorkDescription wd3 = new KPIWorkDescription(LocalDate.of(2026, 6, 26), 6, "Meeting with client");
	                
	                kpiWorkDescRepo.saveAll(Arrays.asList(wd1, wd2, wd3));
					


				}
			}
		};
	}
}
