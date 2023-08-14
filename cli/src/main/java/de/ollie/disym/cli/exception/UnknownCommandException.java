package de.ollie.disym.cli.exception;

import lombok.Getter;

@Getter
public class UnknownCommandException extends RuntimeException {

	private String command;

	public UnknownCommandException(String message, String command) {
		super(message);
		this.command = command;
	}

}
