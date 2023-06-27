package de.ollie.disym.service.model.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.disym.service.model.ConfigurationSetting;

@ExtendWith(MockitoExtension.class)
class GetCsIdTest {

	public static final String IDENTIFIER = "identifier";
	public static final String VALUE = "value";

	private GetCsId unitUnderTest;

	@BeforeEach
	void setUp() {
		unitUnderTest = new GetCsId(ConfigurationSetting.of(IDENTIFIER, VALUE));
	}

	@Nested
	class TestsOfMethod_evaluate_Stack {

		@Test
		void returnsAStackWithTheConfigurationSetting() {
			// Prepare
			Stack<Object> expected = new Stack<Object>();
			expected.push(IDENTIFIER);
			Stack<Object> stack = new Stack<>();
			// Run
			Stack<Object> returned = unitUnderTest.evaluate(stack);
			// Check
			assertEquals(expected, returned);
		}

	}

}