package eu.virac;

import eu.virac.model.KPIRealisation;
import eu.virac.model.KPIWorkDescription;
import eu.virac.model.ProjectContribution;
import eu.virac.model.ProjectInformation;
import eu.virac.model.Users;
import eu.virac.model.ldap.LdapUsers;
import eu.virac.model.enums.EmployeePositions;
import eu.virac.repo.*;
import eu.virac.repo.ldap.IUsersLdapRepo;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.support.LdapNameBuilder;

@SpringBootApplication
public class ProgInzCourseProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgInzCourseProjectApplication.class, args);
	}

	@Bean
	public CommandLineRunner saveDatainDB(IKPI_StatusRepo kpiStatusRepo, IKPICategoriesRepo kpiCatRepo,
			IKPIRealisationRepo kpiRealisationRepo, IKPISubcategoriesRepo kpisubCatRepo,
			IKPIWorkDescriptionRepo kpiWorkDescRepo, IProjectInformationRepo prInfoRepo,
			IProjectContributionRepo prContrRepo, IUsersRepo usersRepo, IUsersLdapRepo ldapUserRepository) {

		return args -> {
			if (prInfoRepo.count() == 0) {

				ProjectInformation p1 = new ProjectInformation(true, "P123", "ProjectAlpha", LocalDate.of(2026, 6, 24),
						LocalDate.of(2027, 6, 24));
				ProjectInformation p2 = new ProjectInformation(false, "P124", "ProjectBravo", LocalDate.of(2026, 6, 25),
						LocalDate.of(2027, 6, 25));
				ProjectInformation p3 = new ProjectInformation(true, "P125", "ProjectEcho", LocalDate.of(2026, 6, 26),
						LocalDate.of(2027, 6, 26));

				prInfoRepo.saveAll(Arrays.asList(p1, p2, p3));

				// These save to MySQL
				Users u1 = new Users("Rodrigo", "Zandbergs", "rodrigo.zandbergs@gmail.com", "********");
				Users u2 = new Users("Maris", "Maize", "maris.maize@inbox.com", "rootadmin");
				Users u3 = new Users("Marcis", "Rupmaize", "marcis.rupmaize@gmail.com", "adminroot");
				usersRepo.saveAll(Arrays.asList(u1, u2, u3));

				ProjectContribution pc1 = new ProjectContribution(EmployeePositions.Darbinieks,
						LocalDate.of(2026, 6, 24), LocalDate.of(2027, 6, 24));
				ProjectContribution pc2 = new ProjectContribution(EmployeePositions.Darbinieks,
						LocalDate.of(2026, 6, 25), LocalDate.of(2027, 6, 25));
				ProjectContribution pc3 = new ProjectContribution(EmployeePositions.Darbinieks,
						LocalDate.of(2026, 6, 26), LocalDate.of(2027, 6, 26));

				pc1.setProjectInformation(p1);
				pc2.setProjectInformation(p2);
				pc3.setProjectInformation(p3);
				pc1.setUser(u1);
				pc2.setUser(u2);
				pc3.setUser(u3);
				prContrRepo.saveAll(Arrays.asList(pc1, pc2, pc3));

				KPIWorkDescription wd1 = new KPIWorkDescription(LocalDate.of(2026, 6, 24), 10, "Debugging my toaster");
				KPIWorkDescription wd2 = new KPIWorkDescription(LocalDate.of(2026, 6, 25), 2,
						"Writting documentations");
				KPIWorkDescription wd3 = new KPIWorkDescription(LocalDate.of(2026, 6, 26), 6, "Meeting with client");

				KPIRealisation kpir1 = new KPIRealisation(LocalDate.now(), "Fixed up the telescope", "wasnt easy :(");
				KPIRealisation kpir2 = new KPIRealisation(LocalDate.now(), "Finally aligned the primary mirrors",
						"took three coffees and a existential crisis");
				KPIRealisation kpir3 = new KPIRealisation(LocalDate.now(), "Calibrated the spectrograph",
						"still don't think it's 100% right but I'm leaving it");
				kpiRealisationRepo.saveAll(Arrays.asList(kpir1, kpir2, kpir3));

				kpiWorkDescRepo.saveAll(Arrays.asList(wd1, wd2, wd3));

				// LDAP

				LdapUsers ldapU1 = new LdapUsers("rzandbergs", "Rodrigo", "Zandbergs", "rodrigo.zandbergs@gmail.com",
						"********");
				ldapU1.setDn(LdapNameBuilder.newInstance().add("uid", "rzandbergs").build());
				ldapUserRepository.save(ldapU1);

				LdapUsers ldapU2 = new LdapUsers("mmaize", "Maris", "Maize", "maris.maize@inbox.com", "rootadmin");
				ldapU2.setDn(LdapNameBuilder.newInstance().add("uid", "mmaize").build());
				ldapUserRepository.save(ldapU2);

				LdapUsers ldapU3 = new LdapUsers("mrupmaize", "Marcis", "Rupmaize", "marcis.rupmaize@gmail.com",
						"adminroot");
				ldapU3.setDn(LdapNameBuilder.newInstance().add("uid", "mrupmaize").build());
				ldapUserRepository.save(ldapU3);
			}
		};
	}
}