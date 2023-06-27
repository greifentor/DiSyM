package de.ollie.disym.service.impl;

import static de.ollie.disym.util.Check.ensure;

import java.util.Stack;

import javax.inject.Named;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.Commands;
import de.ollie.disym.service.model.rule.Rule;
import de.ollie.disym.service.model.rule.RuleEvaluationException;
import de.ollie.disym.service.model.rule.Value;
import de.ollie.disym.service.model.rule.Word;

@Named
public class RuleEvaluatorImpl {

	static final String MESSAGE_CONTAINS_CONTAINED_ARGUMENT_MISSED = "missing argument to check for being contained";
	static final String MESSAGE_CONTAINS_CONTAINING_ARGUMENT_MISSED = "missing argument to be checked for containing another element";
	static final String MESSAGE_MISSING_ARGUMENT = "missing argument {0} for {1} evaluation";
	static final String MESSAGE_WRONG_ARGUMENT_TYPE = "wrong type of argument {0} for {1} evaluation! Should be '{2}' but was '{3}'.";

	public Stack<Object> evaluate(Rule rule, ConfigurationSetting settingToEvaluate) {
		ensure(rule != null, "rule cannot be null!");
		Stack<Object> stack = new Stack<>();
		for (Word word : rule.getWords()) {
			if (word instanceof Command) {
				stack = ((Command) word).evaluate(stack);
			} else if (word == Commands.CONTAINS) {
				ensure(!stack.isEmpty(), new RuleEvaluationException(MESSAGE_CONTAINS_CONTAINED_ARGUMENT_MISSED));
				String contained = stack.pop().toString();
				ensure(!stack.isEmpty(), new RuleEvaluationException(MESSAGE_CONTAINS_CONTAINING_ARGUMENT_MISSED));
				String s = stack.pop().toString();
				stack.push(s.contains(contained));
			} else if (word == Commands.GET_CS_ID) {
				stack.push(settingToEvaluate.getIdentifier());
			} else if (word == Commands.GET_CS_VALUE) {
				stack.push(settingToEvaluate.getValue());
			} else if (word == Commands.OR) {
				ensureBooleanOnTopOfStack(stack, 1, Commands.OR);
				Boolean b1 = (Boolean) stack.pop();
				ensureBooleanOnTopOfStack(stack, 0, Commands.OR);
				Boolean b0 = (Boolean) stack.pop();
				stack.push(b0 || b1);
			} else if (word instanceof Value) {
				stack.push(((Value) word).getValue());
			}
		}
		return stack;
	}

	private void ensureBooleanOnTopOfStack(Stack<Object> stack, int argumentNumber, Commands command) {
		ensure(!stack.isEmpty(), createMissingArgumentException(argumentNumber, command));
		ensure(stack.peek() instanceof Boolean,
				createWrongArgumentTypeException(argumentNumber, command, Boolean.class, stack.peek().getClass()));
	}

	private RuleEvaluationException createMissingArgumentException(int argumentNumber, Commands command) {
		return new RuleEvaluationException(
				MESSAGE_MISSING_ARGUMENT.replace("{0}", "" + argumentNumber).replace("{1}", command.name()));
	}

	private RuleEvaluationException createWrongArgumentTypeException(int argumentNumber, Commands command,
			Class<?> requiredType, Class<?> foundType) {
		return new RuleEvaluationException(
				MESSAGE_WRONG_ARGUMENT_TYPE.replace("{0}", "" + argumentNumber)
						.replace("{1}", command.name())
						.replace("{2}", requiredType.getName())
						.replace("{3}", foundType.getName()));
	}

}
