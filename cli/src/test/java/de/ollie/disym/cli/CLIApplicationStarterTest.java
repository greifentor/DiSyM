package de.ollie.disym.cli;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CLIApplicationStarterTest {

	@MockBean
	private CLIRunner cliRunner;

	@Test
	void callsTheCLIRunnersRunMethod() {
		verify(cliRunner, times(1)).run(any(ApplicationArguments.class));
	}

}
