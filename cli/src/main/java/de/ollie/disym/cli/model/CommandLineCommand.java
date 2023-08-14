package de.ollie.disym.cli.model;

import de.ollie.disym.cli.exception.UnknownCommandException;

public enum CommandLineCommand {

	HELP("help", "show all commands and options."),
	SHOW("show", "prints the result of the application to the console.");

	private String token;
	private String description;

	private CommandLineCommand(String token, String description) {
		this.description = description;
		this.token = token;
	}

	public static CommandLineCommand getByToken(String token) {
		for (CommandLineCommand command : values()) {
			if (command.getToken().equals(token)) {
				return command;
			}
		}
		throw new UnknownCommandException("illegal token for command: " + token, token);
	}

	public String getDescription() {
		return description;
	}

	public String getToken() {
		return token;
	}

}
