package de.ollie.disym.service.impl;

import static de.ollie.disym.util.Check.ensure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import de.ollie.disym.service.CommandFactory;
import de.ollie.disym.service.WordFactory;
import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.Value;

@Named
public class WordFactoryImpl implements WordFactory {

	@Autowired
	private List<CommandFactory<? extends Command>> commandFactories;

	private Map<String, CommandFactory<? extends Command>> mapCommandFactories = new HashMap<>();

	@PostConstruct
	void postConstruct() {
		commandFactories.forEach(factory -> mapCommandFactories.put(factory.create().getToken(), factory));
	}

	@Override
	public Command createCommand(String token) {
		if (isCommand(token)) {
			return mapCommandFactories.get(token).create();
		}
		throw new IllegalArgumentException("there is no command for token: " + token);
	}

	@Override
	public Value createValue(Object value) {
		ensure(value != null, "value cannot be null!");
		return Value.of(value);
	}

	@Override
	public boolean isCommand(String token) {
		return mapCommandFactories.containsKey(token);
	}

}
