package de.ollie.disym.service.impl.command;

import org.springframework.stereotype.Component;

import de.ollie.disym.service.CommandFactory;
import de.ollie.disym.service.model.command.Dup;

@Component
public class DupCommandFactory implements CommandFactory<Dup> {

	@Override
	public Dup create() {
		return new Dup();
	}

}
