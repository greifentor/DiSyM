package de.ollie.disym.service.model.command;

import static de.ollie.disym.util.Check.ensure;

import java.util.Map;
import java.util.Stack;

import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.MissingArgumentRuleEvaluationException;
import de.ollie.disym.service.model.rule.WrongArgumentTypeRuleEvaluationException;

public class Load extends Command {

	public Load() {
		super("LOAD");
	}

	@Override
	public Stack<Object> evaluate(Stack<Object> stack, Map<String, Object> valueStore) {
		ensure(!stack.isEmpty(), new MissingArgumentRuleEvaluationException(0, this));
		ensure(stack.peek() instanceof String,
				new WrongArgumentTypeRuleEvaluationException(0, this, String.class, stack.peek().getClass()));
		String identifier = (String) stack.pop();
		ensure(valueStore.containsKey(identifier), "no value present in value store for identifier: " + identifier);
		stack.push(valueStore.get(identifier));
		return stack;
	}

}
