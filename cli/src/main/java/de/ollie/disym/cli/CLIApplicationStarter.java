package de.ollie.disym.cli;

import javax.inject.Inject;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("de.ollie.disym")
public class CLIApplicationStarter implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(CLIApplicationStarter.class, args);
	}

	@Inject
	private CLIRunner cliRunner;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("run");
		for (String s : args.getSourceArgs()) {
			System.out.println("- " + s);
		}
	}

}
