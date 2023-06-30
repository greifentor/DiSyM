package de.ollie.disym.cli;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.springframework.boot.ApplicationArguments;

import de.ollie.disym.cli.model.CommandLineCommand;

@Named
public class CommandLineCommandChecker {

	public List<CommandLineCommand> check(ApplicationArguments args) {
		List<CommandLineCommand> commands = new ArrayList<>();
		for (String token : args.getNonOptionArgs()) {
			commands.add(CommandLineCommand.getByToken(token));
		}
		return commands;
	}

}
