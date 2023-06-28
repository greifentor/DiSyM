package de.ollie.disym.service.model.command;

import static de.ollie.disym.util.Check.ensure;

import java.util.Map;
import java.util.Stack;

import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.MissingArgumentRuleEvaluationException;

public class Contains extends Command {

	public Contains() {
		super("CONTAINS");
	}

	@Override
	public Stack<Object> evaluate(Stack<Object> stack, Map<String, Object> valueStore) {
		ensure(!stack.isEmpty(), new MissingArgumentRuleEvaluationException(1, this));
		String contained = stack.pop().toString();
		ensure(!stack.isEmpty(), new MissingArgumentRuleEvaluationException(0, this));
		String s = stack.pop().toString();
		stack.push(s.contains(contained));
		return stack;
	}

}
