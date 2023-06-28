package de.ollie.disym.service.model.rule;

import java.util.Map;
import java.util.Stack;

public interface Word {

	Stack<Object> evaluate(Stack<Object> stack, Map<String, Object> valueStore);

}
