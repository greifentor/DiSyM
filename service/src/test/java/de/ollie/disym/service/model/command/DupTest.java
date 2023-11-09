package de.ollie.disym.service.model.command;

import static de.ollie.disym.service.model.rule.MissingArgumentRuleEvaluationException.MESSAGE_MISSING_ARGUMENT;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.Stack;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.disym.service.model.rule.RuleEvaluationException;

@ExtendWith(MockitoExtension.class)
public class DupTest {

	@InjectMocks
	private Dup unitUnderTest;

	@Nested
	class TestsOfMethod_evaluate_Stack {

		@Test
		void throwsAnException_callingTheCommandOnly() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			// Run & Check
			RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
					() -> unitUnderTest.evaluate(stack, Map.of()));
			assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "1").replace("{1}", "DUP"), thrown.getMessage());
		}

		@Test
		void returnsAStackWithDuplicatedTop() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			stack.push(";op");
			// Run
			stack = unitUnderTest.evaluate(stack, Map.of());
			// Check
			assertEquals(2, stack.size());
			assertSame(stack.pop(), stack.pop());
		}

	}

}
