package org.borgoltz.lutins;

import org.borgoltz.lutins.family.FamilyMemberManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class LutinsApplication {

	public static void main(String[] args) {
		try {
			SpringApplication.run(LutinsApplication.class, args);
		} catch (RuntimeException e) {
			if (!e.getClass().getName().equals("org.springframework.boot.devtools.restart.SilentExitExceptionHandler$SilentExitException")) {
				throw e;
			}
			// OK It's just spring restarting...
		}
	}

	@Autowired
	private FamilyMemberManager familyManager;
	
	@EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
		familyManager.initDatabase(false);
    }

}
