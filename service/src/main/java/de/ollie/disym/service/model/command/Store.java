package de.ollie.disym.service.model.command;

import static de.ollie.disym.util.Check.ensure;

import java.util.Map;
import java.util.Stack;

import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.MissingArgumentRuleEvaluationException;
import de.ollie.disym.service.model.rule.WrongArgumentTypeRuleEvaluationException;

public class Store extends Command {

	public Store() {
		super("STORE");
	}

	@Override
	public Stack<Object> evaluate(Stack<Object> stack, Map<String, Object> valueStore) {
		ensure(!stack.isEmpty(), new MissingArgumentRuleEvaluationException(1, this));
		ensure(stack.peek() instanceof String,
				new WrongArgumentTypeRuleEvaluationException(1, this, String.class, stack.peek().getClass()));
		String identifier = (String) stack.pop();
		ensure(!stack.isEmpty(), new MissingArgumentRuleEvaluationException(0, this));
		valueStore.put(identifier, stack.pop());
		return stack;
	}

}
