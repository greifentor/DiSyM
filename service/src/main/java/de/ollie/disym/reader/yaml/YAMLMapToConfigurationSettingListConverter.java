package de.ollie.disym.reader.yaml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Named;

import de.ollie.disym.service.model.ConfigurationSetting;

@Named
public class YAMLMapToConfigurationSettingListConverter {

	public List<ConfigurationSetting> convert(Map<String, Object> map) {
		List<ConfigurationSetting> l = new ArrayList<>();
		fillList(map, "", l);
		return l.stream()
				.sorted((cs0, cs1) -> cs0.getIdentifier().compareTo(cs1.getIdentifier()))
				.collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	private void fillList(Map<String, Object> map, String identifier, List<ConfigurationSetting> l) {
		for (String id : map.keySet()) {
			String idNew = identifier + (!identifier.isEmpty() ? "." : "") + id;
			Object o  = map.get(id);
			if (o instanceof Map) {
				fillList((Map<String, Object>) o, idNew, l);
			} else {
				l.add(new ConfigurationSetting(idNew, "" + o));
			}
		}
	}

}
