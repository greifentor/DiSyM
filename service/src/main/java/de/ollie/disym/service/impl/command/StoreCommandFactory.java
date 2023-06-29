package de.ollie.disym.service.impl.command;

import org.springframework.stereotype.Component;

import de.ollie.disym.service.CommandFactory;
import de.ollie.disym.service.model.command.Store;

@Component
public class StoreCommandFactory implements CommandFactory<Store> {

	@Override
	public Store create() {
		return new Store();
	}

}
