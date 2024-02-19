package incident.Incident;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import incident.Incident.domain.User;
import incident.Incident.service.UserService;

@SpringBootApplication
public class IncidentApplication implements CommandLineRunner {

	private final UserService userSvc;

	public IncidentApplication(UserService userSvc) {
		this.userSvc = userSvc;
	}

	public static void main(String[] args) {
		SpringApplication.run(IncidentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User admin = new User("admin131", "admin131", "admin131", "admin131", "admin131@gmail.com", "Admin123", "ADMIN");
		try {
			this.userSvc.create(admin);
		} catch (Exception e) {
		}
	}

}
