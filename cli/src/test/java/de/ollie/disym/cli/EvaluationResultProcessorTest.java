package de.ollie.disym.cli;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.disym.cli.model.EvaluationResult;

@ExtendWith(MockitoExtension.class)
class EvaluationResultProcessorTest {

	@Mock
	private EvaluationResult evaluationResult;

	@InjectMocks
	private EvaluationResultProcessor unitUnderTest;

	@Test
	void happyRun() {
		// Prepare
		when(evaluationResult.isResult()).thenReturn(true);
		unitUnderTest.add(evaluationResult);
		// Run & Check
		assertDoesNotThrow(() -> unitUnderTest.process());
	}

}
