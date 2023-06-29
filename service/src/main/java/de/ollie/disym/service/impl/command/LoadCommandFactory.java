package de.ollie.disym.service.impl.command;

import org.springframework.stereotype.Component;

import de.ollie.disym.service.CommandFactory;
import de.ollie.disym.service.model.command.Load;

@Component
public class LoadCommandFactory implements CommandFactory<Load> {

	@Override
	public Load create() {
		return new Load();
	}

}
