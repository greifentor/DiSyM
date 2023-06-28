package de.ollie.disym.service.model.command;

import static de.ollie.disym.service.model.rule.MissingArgumentRuleEvaluationException.MESSAGE_MISSING_ARGUMENT;
import static de.ollie.disym.service.model.rule.WrongArgumentTypeRuleEvaluationException.MESSAGE_WRONG_ARGUMENT_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.disym.service.model.rule.RuleEvaluationException;

@ExtendWith(MockitoExtension.class)
public class StoreTest {

	@InjectMocks
	private Store unitUnderTest;

	@Nested
	class TestsOfMethod_evaluate_Stack {

		@Test
		void throwsAnException_callingANDOnly() {
			RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
					() -> unitUnderTest.evaluate(new Stack<>(), Map.of()));
			assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "1").replace("{1}", "STORE"), thrown.getMessage());
		}

		@Test
		void throwsAnException_callingWithOneElementOnStackOnly() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			stack.push("id.enti.fier");
			// Run & Check
			RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
					() -> unitUnderTest.evaluate(stack, Map.of()));
			assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "0").replace("{1}", "STORE"), thrown.getMessage());
		}

		@Test
		void throwsAnException_passingArgument0NotOfTypeString() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			stack.push(Boolean.FALSE);
			// Run & Check
			RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
					() -> unitUnderTest.evaluate(stack, Map.of()));
			assertEquals(
					MESSAGE_WRONG_ARGUMENT_TYPE.replace("{0}", "1")
							.replace("{1}", "STORE")
							.replace("{2}", "java.lang.String")
							.replace("{3}", "java.lang.Boolean"),
					thrown.getMessage());
		}

		@Test
		void returnsAStackWithBooleanTrue_callingWithTwoValues() {
			// Prepare
			String identifier = "id.enti.fier";
			Boolean value = Boolean.TRUE;
			Stack<Object> expected = new Stack<Object>();
			Stack<Object> stack = new Stack<>();
			stack.push(value);
			stack.push(identifier);
			Map<String, Object> valueStore = new HashMap<>();
			// Run
			Stack<Object> returned = unitUnderTest.evaluate(stack, valueStore);
			// Check
			assertEquals(expected, returned);
			assertEquals(value, valueStore.get(identifier));
		}

	}

}
