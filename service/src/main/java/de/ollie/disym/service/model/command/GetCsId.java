package de.ollie.disym.service.model.command;

import java.util.Stack;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.rule.Command;

public class GetCsId extends Command {

	private final ConfigurationSetting configurationSetting;

	public GetCsId(ConfigurationSetting configurationSetting) {
		super("GET_CS_ID");
		this.configurationSetting = configurationSetting;
	}

	@Override
	public Stack<Object> evaluate(Stack<Object> stack) {
		stack.push(configurationSetting.getIdentifier());
		return stack;
	}

}
