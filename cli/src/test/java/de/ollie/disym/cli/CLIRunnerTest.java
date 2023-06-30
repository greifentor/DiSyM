package de.ollie.disym.cli;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import de.ollie.disym.cli.model.EvaluationResult;

@SpringBootTest
class CLIRunnerTest {

	@Mock
	private ApplicationArguments arguments;

	@MockBean
	private EvaluationResultProcessor evaluationResultProcessor;

	@Autowired
	private CLIRunner unitUnderTest;

	@Nested
	class TestsOfMethod_run_ApplicationArguments {

		@Test
		void happyRun() { 
			// Prepare
			when(arguments.containsOption("rule")).thenReturn(true);
			when(arguments.getOptionValues("rule")).thenReturn(List.of("setting.to.evaluate LOAD GET_CS_ID url CONTAINS"));
			when(arguments.containsOption("yamlFile")).thenReturn(true);
			when(arguments.getOptionValues("yamlFile")).thenReturn(List.of("src/test/resources/example-yaml-file.yml"));
			// Run & Check
			assertDoesNotThrow(() -> unitUnderTest.run(arguments));
			verify(evaluationResultProcessor, times(1)).add(EvaluationResult.of("related.systems.doof-url", true));
			verify(evaluationResultProcessor, times(2)).process();
		}

		@Test
		void doesNotThrowAnException_passingAnInvalidRule() {
			// Prepare
			when(arguments.containsOption("rule")).thenReturn(true);
			when(arguments.getOptionValues("rule")).thenReturn(List.of("setting.to.evaluate LOAD GET_CS_ID url CONTAINS"));
			when(arguments.containsOption("yamlFile")).thenReturn(true);
			when(arguments.getOptionValues("yamlFile")).thenReturn(List.of("src/test/resources/does-not-exist.yml"));
			// Run & Check
			assertDoesNotThrow(() -> unitUnderTest.run(arguments));
		}

	}

}
