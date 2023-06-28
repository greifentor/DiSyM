package de.ollie.disym.service.model.command;

import static de.ollie.disym.service.model.rule.MissingArgumentRuleEvaluationException.MESSAGE_MISSING_ARGUMENT;
import static de.ollie.disym.service.model.rule.WrongArgumentTypeRuleEvaluationException.MESSAGE_WRONG_ARGUMENT_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.Stack;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.disym.service.model.rule.RuleEvaluationException;

@ExtendWith(MockitoExtension.class)
public class OrTest {

	@InjectMocks
	private Or unitUnderTest;

	@Nested
	class TestsOfMethod_evaluate_Stack {

		@Test
		void throwsAnException_callingTheCommandOnly() {
			RuleEvaluationException thrown =
					assertThrows(RuleEvaluationException.class, () -> unitUnderTest.evaluate(new Stack<>(), Map.of()));
			assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "1").replace("{1}", "OR"), thrown.getMessage());
		}

		@Test
		void throwsAnException_callingWithOneElementOnStackOnly() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			stack.push(Boolean.TRUE);
			// Run & Check
			RuleEvaluationException thrown =
					assertThrows(RuleEvaluationException.class, () -> unitUnderTest.evaluate(stack, Map.of()));
			assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "0").replace("{1}", "OR"), thrown.getMessage());
		}

		@Test
		void throwsAnException_passingArgument0NotOfTypeBoolean() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			stack.push(";op");
			// Run & Check
			RuleEvaluationException thrown =
					assertThrows(RuleEvaluationException.class, () -> unitUnderTest.evaluate(stack, Map.of()));
			assertEquals(
					MESSAGE_WRONG_ARGUMENT_TYPE
							.replace("{0}", "1")
							.replace("{1}", "OR")
							.replace("{2}", "java.lang.Boolean")
							.replace("{3}", "java.lang.String"),
					thrown.getMessage());
		}

		@ParameterizedTest
		@CsvSource({ "TRUE,TRUE,TRUE", "FALSE,TRUE,TRUE", "TRUE,FALSE,TRUE", "FALSE,FALSE,FALSE" })
		void returnsAStackWithBooleanTrue_callingWithTwoValues(Boolean value0, Boolean value1, Boolean expectedValue) {
			// Prepare
			Stack<Object> expected = new Stack<Object>();
			expected.push(expectedValue);
			Stack<Object> stack = new Stack<>();
			stack.push(value0);
			stack.push(value1);
			// Run
			Stack<Object> returned = unitUnderTest.evaluate(stack, Map.of());
			// Check
			assertEquals(expected, returned);
		}

	}

}
