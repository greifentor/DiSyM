package de.ollie.disym.cli;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.boot.ApplicationArguments;

@Named
public class CLIRunner {

	@PostConstruct
	void postConstruct() {
		System.out.println("post constructed");
	}

	public void run(ApplicationArguments args) {
		System.out.println("run: " + args);
	}

}
