package de.ollie.disym;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiSyMApplicationTest {

	@Test
	void applicationStarts() {
		try {
			DiSyMApplication.main(new String[0]);
		} catch (Exception e) {
			fail("there should not be an application!");
		}
	}

}
