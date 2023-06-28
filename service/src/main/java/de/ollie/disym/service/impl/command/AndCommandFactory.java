package de.ollie.disym.service.impl.command;

import org.springframework.stereotype.Component;

import de.ollie.disym.service.CommandFactory;
import de.ollie.disym.service.model.command.And;

@Component
public class AndCommandFactory implements CommandFactory<And> {

	@Override
	public And create() {
		return new And();
	}

}
