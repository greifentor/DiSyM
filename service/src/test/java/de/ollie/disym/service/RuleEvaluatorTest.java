package de.ollie.disym.service;

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
import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.Rule;
import de.ollie.disym.service.model.rule.Value;

@ExtendWith(MockitoExtension.class)
class RuleEvaluatorTest {

	private static final String ID = "id";
	private static final String VALUE = "value";

	@InjectMocks
	private RuleEvaluator unitUnderTest;

	@Nested
	class TestsOfMethod_evaluate_Rule_ConfigurationSetting {

		@Test
		void throwsAnException_passingANullValueAsRule() {
			assertThrows(IllegalArgumentException.class,
					() -> unitUnderTest.evaluate(null, new ConfigurationSetting("id", ";op")));
		}

		@Test
		void returnsAnEmptyStack_passingAnEmptyRule() {
			assertTrue(
					unitUnderTest.evaluate(new Rule("id", List.of()), new ConfigurationSetting("id", ";op")).isEmpty());
		}

		@Test
		void throwsAnException_callingGET_IDAndPassingANullValueAsConfigurationSetting() {
			assertThrows(NullPointerException.class,
					() -> unitUnderTest.evaluate(new Rule("id", List.of(Command.GET_CS_ID)), null));
		}

		@Test
		void returnsAStackWithConfigurationSettingId_callingGET_CS_IDOnly() {
			// Prepare
			Stack<Object> expected = new Stack<Object>();
			expected.push(ID);
			// Run
			Stack<Object> returned = unitUnderTest.evaluate(new Rule("id", List.of(Command.GET_CS_ID)),
					new ConfigurationSetting(ID, VALUE));
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsAStackWithConfigurationSettingValue_callingGET_CS_VALUEOnly() {
			// Prepare
			Stack<Object> expected = new Stack<Object>();
			expected.push(VALUE);
			// Run
			Stack<Object> returned = unitUnderTest.evaluate(new Rule("id", List.of(Command.GET_CS_VALUE)),
					new ConfigurationSetting(ID, VALUE));
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsAStackWithTheValue_havingAValueWordOnlyInTheRule() {
			// Prepare
			Stack<Object> expected = new Stack<Object>();
			expected.push(VALUE);
			// Run
			Stack<Object> returned = unitUnderTest.evaluate(new Rule("id", List.of(new Value(VALUE))),
					new ConfigurationSetting(ID, ";op"));
			// Check
			assertEquals(expected, returned);
		}
	}

}
