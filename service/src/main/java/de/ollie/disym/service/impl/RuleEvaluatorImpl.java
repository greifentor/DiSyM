package de.ollie.disym.service.impl;

import static de.ollie.disym.util.Check.ensure;

import java.util.Stack;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.rule.Rule;

public class RuleEvaluatorImpl {

	static final String MESSAGE_CONTAINS_CONTAINED_ARGUMENT_MISSED = "missing argument to check for being contained";
	static final String MESSAGE_CONTAINS_CONTAINING_ARGUMENT_MISSED =
			"missing argument to be checked for containing another element";

	public Stack<Object> evaluate(Rule rule, ConfigurationSetting settingToEvaluate) {
		ensure(rule != null, "rule cannot be null!");
		Stack<Object> stack = new Stack<>();
		rule.getWords().forEach(word -> word.evaluate(stack));
		return stack;
	}

}
