package de.ollie.disym.service;

import static de.ollie.disym.util.Check.ensure;

import java.util.Stack;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.Rule;
import de.ollie.disym.service.model.rule.Value;
import de.ollie.disym.service.model.rule.Word;

public class RuleEvaluator {

	public Stack<Object> evaluate(Rule rule, ConfigurationSetting settingToEvaluate) {
		ensure(rule != null, "rule cannot be null!");
		Stack<Object> stack = new Stack<>();
		for (Word word : rule.getWords()) {
			if (word == Command.GET_CS_ID) {
				stack.push(settingToEvaluate.getIdentifier());
			} else if (word == Command.GET_CS_VALUE) {
				stack.push(settingToEvaluate.getValue());
			} else if (word instanceof Value) {
				stack.push(((Value) word).getValue());
			}
		}
		return stack;
	}

}
