package de.ollie.disym.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.command.Or;
import de.ollie.disym.service.model.rule.Rule;
import de.ollie.disym.service.model.rule.Value;

@ExtendWith(MockitoExtension.class)
class RuleEvaluatorImplTest {

	private static final String ID = "id";
	private static final String VALUE = "value";

	@InjectMocks
	private RuleEvaluatorImpl unitUnderTest;

	@Nested
	class TestsOfMethod_evaluate_Rule_ConfigurationSetting {

		@Test
		void throwsAnException_passingANullValueAsRule() {
			assertThrows(
					IllegalArgumentException.class,
					() -> unitUnderTest.evaluate(null, ConfigurationSetting.of("id", ";op")));
		}

		@Test
		void returnsAnEmptyStack_passingAnEmptyRule() {
			assertTrue(
					unitUnderTest.evaluate(Rule.of(List.of()), ConfigurationSetting.of("id", ";op")).isEmpty());
		}

		@Nested
		class TestWithCommands {

			@Test
			void updatesTheStackCorrectly_passingACorrectRuleWithACommand() {
				// Prepare
				Stack<Object> expected = new Stack<>();
				expected.push(Boolean.TRUE);
				// Run
				Stack<Object> returned =
						unitUnderTest
								.evaluate(
										Rule.of(
												List.of(Value.of(Boolean.TRUE), Value.of(Boolean.FALSE), new Or())),
										ConfigurationSetting.of("id", ";op"));
				// Check
				assertEquals(expected, returned);
			}

		}

		@Nested
		class TestsWithValues {

			@Test
			void returnsAStackWithTheValue_havingAValueWordOnlyInTheRule() {
				// Prepare
				Stack<Object> expected = new Stack<Object>();
				expected.push(VALUE);
				// Run
				Stack<Object> returned =
						unitUnderTest
								.evaluate(Rule.of(List.of(Value.of(VALUE))), ConfigurationSetting.of(ID, ";op"));
				// Check
				assertEquals(expected, returned);
			}

		}

	}

}
