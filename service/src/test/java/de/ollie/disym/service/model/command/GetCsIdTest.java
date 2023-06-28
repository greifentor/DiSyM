package de.ollie.disym.service.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.rule.MissingArgumentRuleEvaluationException;
import de.ollie.disym.service.model.rule.WrongArgumentTypeRuleEvaluationException;

@ExtendWith(MockitoExtension.class)
class GetCsIdTest {

	public static final String IDENTIFIER = "identifier";
	public static final String VALUE = "value";

	private GetCsId unitUnderTest;

	@BeforeEach
	void setUp() {
		unitUnderTest = new GetCsId();
	}

	@Nested
	class TestsOfMethod_evaluate_Stack {

		@Test
		void throwsAnException_passingAnEmptyStack() {
			assertThrows(MissingArgumentRuleEvaluationException.class,
					() -> unitUnderTest.evaluate(new Stack<>(), Map.of()));
		}

		@Test
		void throwsAnException_passingAnArgumentOfAWrongType() {
			// Prepare
			Stack<Object> stack = new Stack<>();
			stack.push(";op");
			// Run & Check
			assertThrows(WrongArgumentTypeRuleEvaluationException.class, () -> unitUnderTest.evaluate(stack, Map.of()));
		}

		@Test
		void returnsAStackWithTheIdentifierOfTheConfigurationSetting_passingAStackWithAConfigurationSetting() {
			// Prepare
			Stack<Object> expected = new Stack<Object>();
			expected.push(IDENTIFIER);
			Stack<Object> passed = new Stack<Object>();
			passed.push(ConfigurationSetting.of(IDENTIFIER, VALUE));
			// Run
			Stack<Object> returned = unitUnderTest.evaluate(passed, Map.of());
			// Check
			assertEquals(expected, returned);
		}

	}

}