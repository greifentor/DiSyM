package de.ollie.disym.reader.yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import de.ollie.disym.reader.ReaderException;
import de.ollie.disym.service.model.ConfigurationSetting;
import de.ollie.disym.service.port.ConfigurationSettingYAMLReaderPort;

@Named
public class YAMLReader implements ConfigurationSettingYAMLReaderPort {

	@Inject
	private YAMLMapToConfigurationSettingListConverter yamlMapToConfigurationSettingListConverter;

	@Override
	@SuppressWarnings("unchecked")
	public List<ConfigurationSetting> readYAMLFile(String fileName) {
		try {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			return yamlMapToConfigurationSettingListConverter.convert(mapper.readValue(new File(fileName), Map.class));
		} catch (FileNotFoundException fnfe) {
			throw new ReaderException("file not found: " + fileName, fnfe);
		} catch (IOException ioe) {
			throw new ReaderException("something went wrong while reading: " + fileName, ioe);
		}
	}

}
