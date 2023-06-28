package de.ollie.disym.service.impl.command;

import org.springframework.stereotype.Component;

import de.ollie.disym.service.CommandFactory;
import de.ollie.disym.service.model.command.GetCsValue;

@Component
public class GetScValueCommandFactory implements CommandFactory<GetCsValue> {

	@Override
	public GetCsValue create() {
		return new GetCsValue();
	}

}
