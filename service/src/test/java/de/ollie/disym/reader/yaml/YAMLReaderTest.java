package de.ollie.disym.reader.yaml;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.model.ReaderException;

@ExtendWith(MockitoExtension.class)
class YAMLReaderTest {

	@Spy
	private YAMLMapToConfigurationSettingListConverter converter = new YAMLMapToConfigurationSettingListConverter();

	@InjectMocks
	private YAMLReader unitUnderTest;

	@Nested
	class TestsOfMethod_readYAMLFile_String {

		@Test
		void throwsAnException_passingANotExistingFileName() {
			assertThrows(ReaderException.class, () -> unitUnderTest.readYAMLFile("/a/not/existing/file.name"));
		}

		@Test
		void throwsAnException_passingAFileNameOfANonYAMLFile(@TempDir Path tempDir) throws Exception {
			// Prepare
			Path path = tempDir.resolve("test.yml");
			List<String> content = Arrays.asList("not a YAML file ;o)");
			Files.write(path, content);
			// Run & Check
			assertThrows(ReaderException.class, () -> unitUnderTest.readYAMLFile(path.toString()));
		}

		@Test
		void returnsACorrectMap_passingANameOfAValidYAMLFile(@TempDir Path tempDir) throws Exception {
			// Prepare
			Path path = tempDir.resolve("test.yml");
			List<String> content = Arrays.asList("url:",
					"  subsystem1: url1",
					"  subsystem2: url2",
					"something:",
					"  completely:",
					"    different: Monty Python");
			Files.write(path, content);
			List<ConfigurationSetting> expected = List.of(
					new ConfigurationSetting("something.completely.different", "Monty Python"),
					new ConfigurationSetting("url.subsystem1", "url1"),
					new ConfigurationSetting("url.subsystem2", "url2"));
			// Run
			List<ConfigurationSetting> returned = unitUnderTest.readYAMLFile(path.toString());
			// Check
			assertEquals(expected, returned);
		}

	}

}
