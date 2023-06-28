package de.ollie.disym.service.model.command;

import static de.ollie.disym.util.Check.ensure;

import java.util.Map;
import java.util.Stack;

import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.MissingArgumentRuleEvaluationException;
import de.ollie.disym.service.model.rule.WrongArgumentTypeRuleEvaluationException;

public class And extends Command {

	public And() {
		super("AND");
	}

	@Override
	public Stack<Object> evaluate(Stack<Object> stack, Map<String, Object> valueStore) {
		ensure(!stack.isEmpty(), new MissingArgumentRuleEvaluationException(1, this));
		ensure(stack.peek() instanceof Boolean,
				new WrongArgumentTypeRuleEvaluationException(1, this, Boolean.class, stack.peek().getClass()));
		Boolean b1 = (Boolean) stack.pop();
		ensure(!stack.isEmpty(), new MissingArgumentRuleEvaluationException(0, this));
		ensure(stack.peek() instanceof Boolean,
				new WrongArgumentTypeRuleEvaluationException(0, this, Boolean.class, stack.peek().getClass()));
		Boolean b0 = (Boolean) stack.pop();
		stack.push(b0 && b1);
		return stack;
	}

}
