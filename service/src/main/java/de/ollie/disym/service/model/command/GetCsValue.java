package de.ollie.disym.service.model.command;

import static de.ollie.disym.util.Check.ensure;

import java.util.Map;
import java.util.Stack;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.MissingArgumentRuleEvaluationException;
import de.ollie.disym.service.model.rule.WrongArgumentTypeRuleEvaluationException;

public class GetCsValue extends Command {

	public GetCsValue() {
		super("GET_CS_VALUE");
	}

	@Override
	public Stack<Object> evaluate(Stack<Object> stack, Map<String, Object> valueStore) {
		ensure(!stack.isEmpty(), new MissingArgumentRuleEvaluationException(0, this));
		ensure(stack.peek() instanceof ConfigurationSetting,
				new WrongArgumentTypeRuleEvaluationException(
						0, this, ConfigurationSetting.class, stack.peek().getClass()));
		stack.push(((ConfigurationSetting) stack.pop()).getValue());
		return stack;
	}

}
