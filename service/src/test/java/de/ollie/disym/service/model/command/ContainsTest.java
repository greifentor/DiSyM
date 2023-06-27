package de.ollie.disym.service.model.command;

import static de.ollie.disym.service.model.rule.MissingArgumentRuleEvaluationException.MESSAGE_MISSING_ARGUMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Stack;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.disym.service.model.rule.RuleEvaluationException;

@ExtendWith(MockitoExtension.class)
public class ContainsTest {

	@InjectMocks
	private Contains unitUnderTest;

	@Nested
	class TestsOfMethod_evaluate_Stack {

		@Test
		void throwsAnException_callingTheCommandOnly() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			// Run & Check
			RuleEvaluationException thrown =
					assertThrows(RuleEvaluationException.class, () -> unitUnderTest.evaluate(stack));
			assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "1").replace("{1}", "CONTAINS"), thrown.getMessage());
		}

		@Test
		void throwsAnException_callingWithOneElementOnStackOnly() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			stack.push(";op");
			// Run & Check
			RuleEvaluationException thrown =
					assertThrows(RuleEvaluationException.class, () -> unitUnderTest.evaluate(stack));
			assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "0").replace("{1}", "CONTAINS"), thrown.getMessage());
		}

		@Test
		void returnsAStackWithBooleanTrue_callingWithTwoValuesSecondIsContainedByTheFirst() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			stack.push("value");
			stack.push("al");
			Stack<Object> expected = new Stack<Object>();
			expected.push(Boolean.TRUE);
			// Run
			Stack<Object> returned = unitUnderTest.evaluate(stack);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsAStackWithBooleanFalse_callingWithTwoValuesSecondIsNotContainedByTheFirst() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			stack.push(";op");
			stack.push("value");
			Stack<Object> expected = new Stack<Object>();
			expected.push(Boolean.FALSE);
			// Run
			Stack<Object> returned = unitUnderTest.evaluate(stack);
			// Check
			assertEquals(expected, returned);
		}

	}

}
