package de.ollie.disym.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import de.ollie.disym.cli.model.CommandLineCommand;

@ExtendWith(MockitoExtension.class)
class CommandLineCommandCheckerTest {

	@Mock
	private ApplicationArguments arguments;

	@InjectMocks
	private CommandLineCommandChecker unitUnderTest;

	@Nested
	class TestsOfMethod_check_ApplicationArguments {

		@Test
		void throwsAnException_passingAnUnknownCommand() {
			when(arguments.getNonOptionArgs()).thenReturn(List.of(";op"));
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.check(arguments));
		}

		@ParameterizedTest
		@EnumSource(value = CommandLineCommand.class)
		void returnsACorrectListOfCommandLineCommands_passingAKnownCommand(CommandLineCommand expected) {
			when(arguments.getNonOptionArgs()).thenReturn(List.of(expected.getToken()));
			assertEquals(expected, unitUnderTest.check(arguments).get(0));
		}

	}

}
