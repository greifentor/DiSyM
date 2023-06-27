package de.ollie.disym.service.model.command;

import java.util.Stack;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.rule.Command;

public class GetCsValue extends Command {

	private final ConfigurationSetting configurationSetting;

	public GetCsValue(ConfigurationSetting configurationSetting) {
		super("GET_CS_VALUE");
		this.configurationSetting = configurationSetting;
	}

	@Override
	public Stack<Object> evaluate(Stack<Object> stack) {
		stack.push(configurationSetting.getValue());
		return stack;
	}

}
