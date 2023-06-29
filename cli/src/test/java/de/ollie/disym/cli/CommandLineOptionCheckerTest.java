package de.ollie.disym.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.ApplicationArguments;

import de.ollie.disym.cli.model.Argument;
import de.ollie.disym.cli.model.CommandLineOption;
import de.ollie.disym.cli.model.CommandLineOption.Type;

@ExtendWith(MockitoExtension.class)
class CommandLineOptionCheckerTest {

	private static final String NAME = "name";
	private static final String VALUE = "value";

	@Mock
	private ApplicationArguments args;

	@InjectMocks
	private CommandLineOptionChecker unitUnderTest;

	@Nested
	class TestsOfMethod_check_ApplicationArguments_CommandLineOptionArr {

		@Test
		void throwsAnException_passingApplicationArgumentsAsNullValue() {
			assertThrows(
					IllegalArgumentException.class,
					() -> unitUnderTest.check(null, CommandLineOption.of("argName", "bla", true, Type.STRING)));
		}

		@Test
		void throwsAnException_passingCommandLineOptionAsNullValue() {
			assertThrows(NullPointerException.class, () -> unitUnderTest.check(args, null));
		}

		@Test
		void throwsAnException_passingParametersWhereARequiredCommandLineOptionIsSetButNoMatchingArgumentPassed() {
			assertThrows(
					IllegalArgumentException.class,
					() -> unitUnderTest.check(args, CommandLineOption.of("arg", "-", true, Type.STRING)));
		}

		@Test
		void throwsAnException_passingParametersWhereACommandLineOptionIsSetButWithTheWrongType() {
			// Prepare
			when(args.containsOption(NAME)).thenReturn(true);
			when(args.getOptionValues(NAME)).thenReturn(List.of(VALUE));
			// Run & Check
			assertThrows(
					IllegalArgumentException.class,
					() -> unitUnderTest.check(args, CommandLineOption.of(NAME, "-", false, Type.LONG)));
		}

		@Test
		void returnsAnEmptyList_passingArgsButNoCommandLineOptions() {
			assertTrue(unitUnderTest.check(args).isEmpty());
		}

		@Test
		void returnsAListWithACorrectArgument_passingANotRequiredCommandLineOptionAndAMatchingArgument() {
			// Prepare
			when(args.containsOption(NAME)).thenReturn(true);
			when(args.getOptionValues(NAME)).thenReturn(List.of(VALUE));
			List<Argument> expected = List.of(Argument.of(NAME, List.of(VALUE)));
			// Run
			List<Argument> returned = unitUnderTest.check(args, CommandLineOption.of(NAME, "-", false, Type.STRING));
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsAListWithACorrectArgument_passingANotRequiredCommandLineOptionOfTypeBooleanAndAMatchingArgument() {
			// Prepare
			when(args.containsOption(NAME)).thenReturn(true);
			when(args.getOptionValues(NAME)).thenReturn(List.of("true"));
			List<Argument> expected = List.of(Argument.of(NAME, List.of(Boolean.TRUE)));
			// Run
			List<Argument> returned = unitUnderTest.check(args, CommandLineOption.of(NAME, "-", false, Type.BOOLEAN));
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsAListWithACorrectArgument_passingANotRequiredCommandLineOptionOfTypeLongAndAMatchingArgument() {
			// Prepare
			long value = 4711;
			when(args.containsOption(NAME)).thenReturn(true);
			when(args.getOptionValues(NAME)).thenReturn(List.of("" + value));
			List<Argument> expected = List.of(Argument.of(NAME, List.of(value)));
			// Run
			List<Argument> returned = unitUnderTest.check(args, CommandLineOption.of(NAME, "-", false, Type.LONG));
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsAListWithACorrectArgument_passingANotRequiredCommandLineOptionOfTypeStringAndAMatchingArgument() {
			// Prepare
			when(args.containsOption(NAME)).thenReturn(true);
			when(args.getOptionValues(NAME)).thenReturn(List.of(VALUE));
			List<Argument> expected = List.of(Argument.of(NAME, List.of(VALUE)));
			// Run
			List<Argument> returned = unitUnderTest.check(args, CommandLineOption.of(NAME, "-", false, Type.STRING));
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsAListWithACorrectArgument_passingARequiredCommandLineOptionOfTypeStringAndAMatchingArgument() {
			// Prepare
			when(args.containsOption(NAME)).thenReturn(true);
			when(args.getOptionValues(NAME)).thenReturn(List.of(VALUE));
			List<Argument> expected = List.of(Argument.of(NAME, List.of(VALUE)));
			// Run
			List<Argument> returned = unitUnderTest.check(args, CommandLineOption.of(NAME, "-", true, Type.STRING));
			// Check
			assertEquals(expected, returned);
		}

	}

}
