package de.ollie.disym.cli;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.io.PrintStream;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HelpPrinterTest {

	@Mock
	private PrintStream printStream;

	@InjectMocks
	private HelpPrinter unitUnderTest;

	@Nested
	class TestsOfMethod_print_PrintStream_CommandLineOptionArray {

		@Test
		void throwsAnException_passingCommandLineOptionsAsNullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.print(printStream, null));
		}

		@Test
		void throwsAnException_passingPrintStreamAsNullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.print(null, CLIRunner.OPTIONS));
		}

		@Test
		void printsSomethingToThePrintStream() {
			unitUnderTest.print(printStream, CLIRunner.OPTIONS);
			verify(printStream, atLeast(6)).println(anyString());
		}

	}

}
