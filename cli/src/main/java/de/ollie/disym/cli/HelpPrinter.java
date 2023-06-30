package de.ollie.disym.cli;

import static de.ollie.disym.util.Check.ensure;

import java.io.PrintStream;
import java.util.Arrays;

import javax.inject.Named;

import de.ollie.disym.cli.model.CommandLineCommand;
import de.ollie.disym.cli.model.CommandLineOption;

@Named
public class HelpPrinter {

	public void print(PrintStream printStream, CommandLineOption[] options) {
		ensure(options != null, "options to show cannot be null!");
		ensure(printStream != null, "print stream cannot be null!");
		printStream.println("\nUSAGE: [COMMANDS] [OPTIONS]");
		printStream.println("\n  COMMANDS:");
		Arrays.asList(CommandLineCommand.values())
				.stream()
				.map(command -> String.format("    %-10s - %s", command.getToken(), command.getDescription()))
				.forEach(printStream::println);
		printStream.println("\n  OPTIONS:");
		Arrays.asList(options)
				.stream()
				.map(option -> String.format("    %-10s - %s", option.getName(), option.getDescription()))
				.forEach(printStream::println);
	}

}
