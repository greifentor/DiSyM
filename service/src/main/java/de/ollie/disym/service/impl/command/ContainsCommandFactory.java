package de.ollie.disym.service.impl.command;

import javax.inject.Named;

import de.ollie.disym.service.CommandFactory;
import de.ollie.disym.service.model.command.Contains;

@Named
public class ContainsCommandFactory implements CommandFactory<Contains> {

	@Override
	public Contains create() {
		return new Contains();
	}

}
