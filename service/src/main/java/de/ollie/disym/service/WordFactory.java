package de.ollie.disym.service;

import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.Value;

public interface WordFactory {

	Command createCommand(String token);

	Value createValue(Object value);

	boolean isCommand(String token);

}
