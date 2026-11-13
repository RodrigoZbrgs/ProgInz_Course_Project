package eu.virac;

import eu.virac.model.Department;
import eu.virac.model.KPICategories;
import eu.virac.model.KPIRealisation;
import eu.virac.model.KPIWorkDescription;
import eu.virac.model.KPI_Statuss;
import eu.virac.model.KPI_Subcategories;
import eu.virac.model.ProjectContribution;
import eu.virac.model.ProjectInformation;
import eu.virac.model.Users;
import eu.virac.model.enums.EmployeePositions;
import eu.virac.model.enums.Priority;
import eu.virac.model.enums.WDStatus;
import eu.virac.model.security.MyAuthority;
import eu.virac.repo.IDepartmentRepo;
import eu.virac.repo.IKPICategoriesRepo;
import eu.virac.repo.IKPIRealisationRepo;
import eu.virac.repo.IKPISubcategoriesRepo;
import eu.virac.repo.IKPIWorkDescriptionRepo;
import eu.virac.repo.IKPI_StatusRepo;
import eu.virac.repo.IProjectContributionRepo;
import eu.virac.repo.IProjectInformationRepo;
import eu.virac.repo.IUsersRepo;
import eu.virac.repo.security.IMyAuthorityRepo;

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
			IProjectContributionRepo prContrRepo, IUsersRepo usersRepo, IMyAuthorityRepo authRepo,
			IDepartmentRepo depRepo) {

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
					Department dep1 = new Department("Programming", "Clean coding");
					Department dep2 = new Department("Manegment", "Ower seers");
					Department dep3 = new Department("Workers", "Work hard");

					depRepo.saveAll(Arrays.asList(dep1, dep2, dep3));

					
					MyAuthority auth1 = new MyAuthority("ADMIN");
					MyAuthority auth2 = new MyAuthority("USER");
					authRepo.saveAll(Arrays.asList(auth1, auth2));
					Users u1 = new Users("Rodrigo", "Zandbergs", "rodrigo.zandbergs@gmail.com", "********", dep1);
					//u1.setUsername("Rodrigo");
					Users u2 = new Users("Maris", "Maize", "maris.maize@inbox.com", "rootadmin", dep2);
					//u2.setUsername("Maris");
					Users u3 = new Users("Marcis", "Rupmaize", "marcis.rupmaize@gmail.com", "adminroot", dep3);
					//u3.setUsername("Marics");
					// TODO uztaisīt, ka vns darbinieks ir divos projektos jeb piesiastīts diviem
					// ProjectContrubution
					usersRepo.saveAll(Arrays.asList(u1, u2, u3));
					auth1.addUser(u1);
					auth2.addUser(u1);
					auth2.addUser(u2);
					auth2.addUser(u3);
					authRepo.saveAll(Arrays.asList(auth1, auth2));

					KPICategories cat1 = new KPICategories("Programmatūras atjaunošana", "Info");
					KPICategories cat2 = new KPICategories("Projekta izveide", "Info");
					kpiCatRepo.saveAll(Arrays.asList(cat1, cat2));

					KPI_Subcategories subcat1 = new KPI_Subcategories("Eclipse", "INfo sifdjhfd", cat1);
					KPI_Subcategories subcat2 = new KPI_Subcategories("Ubuntu", "INfo sifdjhffjhjd", cat1);
					KPI_Subcategories subcat3 = new KPI_Subcategories("LZP jaunais projekts", "INfo dadadsifdjhfd",
							cat2);
					kpisubCatRepo.saveAll(Arrays.asList(subcat1, subcat2, subcat3));

					ProjectContribution pc1 = new ProjectContribution(EmployeePositions.Darbinieks,
							LocalDate.of(2026, 6, 24), LocalDate.of(2027, 6, 24));
					ProjectContribution pc2 = new ProjectContribution(EmployeePositions.Novada_Vaditajs,
							LocalDate.of(2026, 6, 25), LocalDate.of(2027, 6, 25));
					ProjectContribution pc3 = new ProjectContribution(EmployeePositions.Novada_Vaditajs,
							LocalDate.of(2026, 6, 26), LocalDate.of(2027, 6, 26));

					pc1.setUser(u1);
					pc2.setUser(u2);
					pc3.setUser(u3);
					pc1.setProjectInformation(p1);
					pc2.setProjectInformation(p2);
					pc3.setProjectInformation(p3);
					prContrRepo.saveAll(Arrays.asList(pc1, pc2, pc3));
					KPIWorkDescription wd1 = new KPIWorkDescription(LocalDate.of(2026, 6, 24), Priority.High,

							"Debugging my toaster", p1, dep1, subcat1, pc1);
					KPIWorkDescription wd2 = new KPIWorkDescription(LocalDate.of(2026, 6, 25), Priority.Low,
							"Writting documentations", p2, dep1, subcat2, pc2);
					KPIWorkDescription wd3 = new KPIWorkDescription(LocalDate.of(2026, 6, 26), Priority.Medium,
							"Meeting with client", p3, dep2, subcat3, pc3);
					KPIWorkDescription wd4 = new KPIWorkDescription(LocalDate.of(2026, 6, 27), Priority.High,
							"Refactored a method so clean even I don't recognize it", p1, dep1, subcat1, pc2);
					KPIWorkDescription wd5 = new KPIWorkDescription(LocalDate.of(2026, 6, 28), Priority.High,
							"Hunted a bug for six hours, turned out to be a typo", p1, dep1, subcat2, pc2);
					KPIWorkDescription wd6 = new KPIWorkDescription(LocalDate.of(2026, 6, 29), Priority.High,
							"Wrote unit tests that I will probably never run again", p2, dep1, subcat3, pc1);
					KPIWorkDescription wd7 = new KPIWorkDescription(LocalDate.of(2026, 6, 30), Priority.Low,
							"Argued with the compiler and lost gracefully", p1, dep1, subcat1, pc1);
					KPIWorkDescription wd8 = new KPIWorkDescription(LocalDate.of(2026, 7, 1), Priority.Medium,
							"Renamed variables until the code looked professional", p3, dep1, subcat2, pc3);
					KPIWorkDescription wd9 = new KPIWorkDescription(LocalDate.of(2026, 7, 2), Priority.Medium,
							"Reviewed a pull request and left 47 comments", p1, dep2, subcat1, pc3);
					KPIWorkDescription wd10 = new KPIWorkDescription(LocalDate.of(2026, 7, 3), Priority.Medium,
							"Optimized a query that nobody was complaining about", p2, dep1, subcat2, pc3);
					KPIWorkDescription wd11 = new KPIWorkDescription(LocalDate.of(2026, 7, 4), Priority.Low,
							"Deleted 300 lines of dead code and felt alive", p3, dep1, subcat1, pc2);
					KPIWorkDescription wd12 = new KPIWorkDescription(LocalDate.of(2026, 7, 5), Priority.Low,
							"Attended a meeting that could have been an email", p1, dep2, subcat2, pc2);
					KPIWorkDescription wd13 = new KPIWorkDescription(LocalDate.of(2026, 7, 6), Priority.Low,
							"Fixed the bug, introduced two new ones, called it progress", p2, dep1, subcat3, pc2);
					KPIWorkDescription wd14 = new KPIWorkDescription(LocalDate.of(2026, 7, 7), Priority.Low,
							"Deployed on a Friday and prayed", p3, dep1, subcat3, pc1);
					KPIWorkDescription wd15 = new KPIWorkDescription(LocalDate.of(2026, 7, 8), Priority.High,
							"Updated the README and pretended that counts as coding", p1, dep1, subcat1, pc1);

					kpiWorkDescRepo.saveAll(Arrays.asList(wd1, wd2, wd3, wd4, wd5, wd6, wd7, wd8, wd9, wd10, wd11, wd12,
							wd13, wd14, wd15));
					// visiem WD sākuma statusu vajadžeut uztaiīt TODO
					KPI_Statuss st1 = new KPI_Statuss(WDStatus.TODO, LocalDate.now().minusDays(2), wd1);
					KPI_Statuss st2 = new KPI_Statuss(WDStatus.IN_PROGRESS, LocalDate.now().minusDays(1), wd1);
					KPI_Statuss st3 = new KPI_Statuss(WDStatus.COMPLETED, LocalDate.now(), wd1);
					KPI_Statuss st4 = new KPI_Statuss(WDStatus.COMPLETED, LocalDate.now().plusDays(2), wd3);
					kpiStatusRepo.saveAll(Arrays.asList(st1, st2, st3, st4));

					KPIRealisation kpir1 = new KPIRealisation(LocalDate.of(2026, 3, 24), "Fixed up the telescope",
							"wasnt easy :(", wd1, p1);
					
					KPIRealisation kpir2 = new KPIRealisation(LocalDate.of(2026, 3, 24),
							"Finally aligned the primary mirrors", "took three coffees and a existential crisis", wd2, p2);
					KPIRealisation kpir3 = new KPIRealisation(LocalDate.of(2026, 3, 24), "Calibrated the spectrograph",
							"still don't think it's 100% right but I'm leaving it", wd3, p3);
					kpiRealisationRepo.saveAll(Arrays.asList(kpir1, kpir2, kpir3));

				}
			}
		};
	}
}