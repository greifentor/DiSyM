package de.ollie.disym.service;

import de.ollie.disym.service.model.rule.Command;

public interface CommandFactory<T extends Command> {

	T create();

}
