package de.ollie.disym.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.ollie.disym.service.model.command.And;
import de.ollie.disym.service.model.rule.Rule;
import de.ollie.disym.service.model.rule.Value;

@SpringBootTest
class StringToRuleConverterImplTest {

	@Autowired
	private StringToRuleConverterImpl unitUnderTest;

	@Nested
	class TestsOfMethod_convert_String {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.convert(null));
		}

		@Test
		void returnsAEmptyRule_passingAnEmptyString() {
			Rule expected = Rule.of(List.of());
			assertEquals(expected, unitUnderTest.convert(""));
		}

		@Test
		void returnsACorrectRule_passingAStringWithCorrectTokens() {
			Rule expected = Rule.of(List.of(Value.of(Boolean.TRUE), Value.of(Boolean.TRUE), new And()));
			assertEquals(expected, unitUnderTest.convert("true true AND"));
		}

	}

	@Nested
	class TestsOfMethod_getValue_String {

		@ParameterizedTest
		@CsvSource({ "false,FALSE", "False,FALSE", "FALSE,FALSE", "true,TRUE", "TRUE,TRUE", "True,TRUE" })
		void returnsCorrectBooleans_passingAStringWithBooleanValue(String passed, Boolean expected) {
			assertEquals(expected, unitUnderTest.getValue(passed));
		}

		@Test
		void returnsCorrectString_passingAStringWithStringValue() {
			String s = ";op";
			assertEquals(s, unitUnderTest.getValue(s));
		}

		@Test
		void returnsCorrectString_passingAStringWithStringValueInSingleQuotes() {
			String s = "'true false bla bla'";
			assertEquals(s, unitUnderTest.getValue(s));
		}

		@Test
		void returnsCorrectString_passingAStringWithStringValueInDoubleQuotes() {
			String s = "\"true false bla bla\"";
			assertEquals(s, unitUnderTest.getValue(s));
		}

	}

}
