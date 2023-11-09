package de.ollie.disym.service.impl.command;

import org.springframework.stereotype.Component;

import de.ollie.disym.service.CommandFactory;
import de.ollie.disym.service.model.command.Print;

@Component
public class PrintCommandFactory implements CommandFactory<Print> {

	@Override
	public Print create() {
		return new Print();
	}

}
