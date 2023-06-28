package de.ollie.disym.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.ollie.disym.service.model.command.And;
import de.ollie.disym.service.model.rule.Command;
import de.ollie.disym.service.model.rule.Value;

@SpringBootTest
class WordFactoryImplTest {

	private static final String VALUE = "value";

	@Autowired
	private WordFactoryImpl unitUnderTest;

	@Nested
	class TestsOfMethod_createCommand_Object {

		@Test
		void throwsAnException_passingATokenWhichIsNotACommand() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.createCommand(";op"));
		}

		@Test
		void returnsTheCorrectCommand_passingATokenWhichIsACommand() {
			Command returned = unitUnderTest.createCommand("AND");
			assertTrue(returned instanceof And);
		}

	}

	@Nested
	class TestsOfMethod_createValue_Object {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.createValue(null));
		}

		@Test
		void setsThePassedValue_passingAValue() {
			Value expected = Value.of(VALUE);
			assertEquals(expected, unitUnderTest.createValue(VALUE));
		}

	}

	@Nested
	class TestsOfMethod_isCommand_String {

		@Test
		void returnsFalse_passingATokenWhichIsNotACommand() {
			assertFalse(unitUnderTest.isCommand(";op"));
		}

		@ParameterizedTest
		@CsvSource(value = { "AND", "CONTAINS", "GET_CS_ID", "GET_CS_VALUE", "OR" })
		void returnsTrue_passingATokenWhichIsACommand(String token) {
			assertTrue(unitUnderTest.isCommand(token));
		}

	}

}
