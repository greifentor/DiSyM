package de.ollie.disym.service.port;

import java.util.List;

import de.ollie.disym.service.model.ConfigurationSetting;

public interface ConfigurationSettingYAMLReaderPort {

	List<ConfigurationSetting> readYAMLFile(String fileName);

}
