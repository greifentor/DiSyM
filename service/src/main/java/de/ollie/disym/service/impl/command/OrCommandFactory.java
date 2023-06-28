package de.ollie.disym.service.impl.command;

import org.springframework.stereotype.Component;

import de.ollie.disym.service.CommandFactory;
import de.ollie.disym.service.model.command.Or;

@Component
public class OrCommandFactory implements CommandFactory<Or> {

	@Override
	public Or create() {
		return new Or();
	}

}
