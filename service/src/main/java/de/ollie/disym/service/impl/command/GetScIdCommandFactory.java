package de.ollie.disym.service.impl.command;

import org.springframework.stereotype.Component;

import de.ollie.disym.service.CommandFactory;
import de.ollie.disym.service.model.command.GetCsId;

@Component
public class GetScIdCommandFactory implements CommandFactory<GetCsId> {

	@Override
	public GetCsId create() {
		return new GetCsId();
	}

}
