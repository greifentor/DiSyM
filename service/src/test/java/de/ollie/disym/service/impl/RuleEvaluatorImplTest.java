package de.ollie.disym.service.impl;

import static de.ollie.disym.service.impl.RuleEvaluatorImpl.MESSAGE_CONTAINS_CONTAINED_ARGUMENT_MISSED;
import static de.ollie.disym.service.impl.RuleEvaluatorImpl.MESSAGE_CONTAINS_CONTAINING_ARGUMENT_MISSED;
import static de.ollie.disym.service.impl.RuleEvaluatorImpl.MESSAGE_MISSING_ARGUMENT;
import static de.ollie.disym.service.impl.RuleEvaluatorImpl.MESSAGE_WRONG_ARGUMENT_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.command.And;
import de.ollie.disym.service.model.rule.Commands;
import de.ollie.disym.service.model.rule.Rule;
import de.ollie.disym.service.model.rule.RuleEvaluationException;
import de.ollie.disym.service.model.rule.Value;

@SpringBootTest
class RuleEvaluatorImplTest {

	private static final String ID = "id";
	private static final String VALUE = "value";

	@Autowired
	private RuleEvaluatorImpl unitUnderTest;

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

		@Nested
		class Command_And {

			@Test
			void throwsAnException_callingANDOnly() {
				RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
						() -> unitUnderTest.evaluate(new Rule("id", List.of(new And())), null));
				assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "1").replace("{1}", "AND"), thrown.getMessage());
			}

			@Test
			void throwsAnException_callingWithOneElementOnStackOnly() {
				RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
						() -> unitUnderTest.evaluate(new Rule("id", List.of(Value.of(Boolean.TRUE), new And())),
								null));
				assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "0").replace("{1}", "AND"), thrown.getMessage());
			}

			@Test
			void throwsAnException_passingArgument0NotOfTypeBoolean() {
				RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
						() -> unitUnderTest.evaluate(new Rule("id", List.of(Value.of(";op"), new And())), null));
				assertEquals(
						MESSAGE_WRONG_ARGUMENT_TYPE.replace("{0}", "1")
								.replace("{1}", "AND")
								.replace("{2}", "java.lang.Boolean")
								.replace("{3}", "java.lang.String"),
						thrown.getMessage());
			}

			@ParameterizedTest
			@CsvSource({ "TRUE,TRUE,TRUE", "FALSE,TRUE,FALSE", "TRUE,FALSE,FALSE", "FALSE,FALSE,FALSE" })
			void returnsAStackWithBooleanTrue_callingWithTwoValues(Boolean value0, Boolean value1,
					Boolean expectedValue) {
				// Prepare
				Stack<Object> expected = new Stack<Object>();
				expected.push(expectedValue);
				// Run
				Stack<Object> returned = unitUnderTest.evaluate(
						new Rule("id", List.of(Value.of(value0), Value.of(value1), new And())),
						new ConfigurationSetting(ID, VALUE));
				// Check
				assertEquals(expected, returned);
			}

		}

		@Nested
		class Command_CONTAINS {

			@Test
			void throwsAnException_callingCONTAINSOnly() {
				RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
						() -> unitUnderTest.evaluate(new Rule("id", List.of(Commands.CONTAINS)), null));
				assertEquals(MESSAGE_CONTAINS_CONTAINED_ARGUMENT_MISSED, thrown.getMessage());
			}

			@Test
			void throwsAnException_callingWithOneElementOnStackOnly() {
				RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
						() -> unitUnderTest.evaluate(new Rule("id", List.of(Value.of(VALUE), Commands.CONTAINS)), null));
				assertEquals(MESSAGE_CONTAINS_CONTAINING_ARGUMENT_MISSED, thrown.getMessage());
			}

			@Test
			void returnsAStackWithBooleanTrue_callingWithTwoValuesSecondIsContainedByTheFirst() {
				// Prepare
				Stack<Object> expected = new Stack<Object>();
				expected.push(Boolean.TRUE);
				// Run
				Stack<Object> returned = unitUnderTest.evaluate(
						new Rule("id", List.of(Value.of(VALUE), Value.of("al"), Commands.CONTAINS)),
						new ConfigurationSetting(ID, VALUE));
				// Check
				assertEquals(expected, returned);
			}

			@Test
			void returnsAStackWithBooleanFalse_callingWithTwoValuesSecondIsNotContainedByTheFirst() {
				// Prepare
				Stack<Object> expected = new Stack<Object>();
				expected.push(Boolean.FALSE);
				// Run
				Stack<Object> returned = unitUnderTest.evaluate(
						new Rule("id", List.of(Value.of(VALUE), Value.of(";op"), Commands.CONTAINS)),
						new ConfigurationSetting(ID, VALUE));
				// Check
				assertEquals(expected, returned);
			}

		}

		@Nested
		class Command_GET_CS_ID {

			@Test
			void throwsAnException_callingGET_CS_IDAndPassingANullValueAsConfigurationSetting() {
				assertThrows(NullPointerException.class,
						() -> unitUnderTest.evaluate(new Rule("id", List.of(Commands.GET_CS_ID)), null));
			}

			@Test
			void returnsAStackWithConfigurationSettingId_callingGET_CS_IDOnly() {
				// Prepare
				Stack<Object> expected = new Stack<Object>();
				expected.push(ID);
				// Run
				Stack<Object> returned = unitUnderTest.evaluate(new Rule("id", List.of(Commands.GET_CS_ID)),
						new ConfigurationSetting(ID, VALUE));
				// Check
				assertEquals(expected, returned);
			}

		}

		@Nested
		class Command_GET_CS_VALUE {

			@Test
			void returnsAStackWithConfigurationSettingValue_callingGET_CS_VALUEOnly() {
				// Prepare
				Stack<Object> expected = new Stack<Object>();
				expected.push(VALUE);
				// Run
				Stack<Object> returned = unitUnderTest.evaluate(new Rule("id", List.of(Commands.GET_CS_VALUE)),
						new ConfigurationSetting(ID, VALUE));
				// Check
				assertEquals(expected, returned);
			}

		}

		@Nested
		class Values {

			@Test
			void returnsAStackWithTheValue_havingAValueWordOnlyInTheRule() {
				// Prepare
				Stack<Object> expected = new Stack<Object>();
				expected.push(VALUE);
				// Run
				Stack<Object> returned = unitUnderTest.evaluate(new Rule("id", List.of(Value.of(VALUE))),
						new ConfigurationSetting(ID, ";op"));
				// Check
				assertEquals(expected, returned);
			}

		}

		@Nested
		class Command_OR {

			@Test
			void throwsAnException_callingOROnly() {
				RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
						() -> unitUnderTest.evaluate(new Rule("id", List.of(Commands.OR)), null));
				assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "1").replace("{1}", "OR"), thrown.getMessage());
			}

			@Test
			void throwsAnException_callingWithOneElementOnStackOnly() {
				RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
						() -> unitUnderTest.evaluate(new Rule("id", List.of(Value.of(Boolean.TRUE), Commands.OR)),
								null));
				assertEquals(MESSAGE_MISSING_ARGUMENT.replace("{0}", "0").replace("{1}", "OR"), thrown.getMessage());
			}

			@Test
			void throwsAnException_passingArgument0NotOfTypeBoolean() {
				RuleEvaluationException thrown = assertThrows(RuleEvaluationException.class,
						() -> unitUnderTest.evaluate(new Rule("id", List.of(Value.of(";op"), Commands.OR)), null));
				assertEquals(
						MESSAGE_WRONG_ARGUMENT_TYPE.replace("{0}", "1")
								.replace("{1}", "OR")
								.replace("{2}", "java.lang.Boolean")
								.replace("{3}", "java.lang.String"),
						thrown.getMessage());
			}

			@ParameterizedTest
			@CsvSource({ "TRUE,TRUE,TRUE", "FALSE,TRUE,TRUE", "TRUE,FALSE,TRUE", "FALSE,FALSE,FALSE" })
			void returnsAStackWithBooleanTrue_callingWithTwoValues(Boolean value0, Boolean value1,
					Boolean expectedValue) {
				// Prepare
				Stack<Object> expected = new Stack<Object>();
				expected.push(expectedValue);
				// Run
				Stack<Object> returned = unitUnderTest.evaluate(
						new Rule("id", List.of(Value.of(value0), Value.of(value1), Commands.OR)),
						new ConfigurationSetting(ID, VALUE));
				// Check
				assertEquals(expected, returned);
			}

		}

	}

}
