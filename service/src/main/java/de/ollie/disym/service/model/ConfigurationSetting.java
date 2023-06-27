package de.ollie.disym.service.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@Generated
@Getter
@ToString
public class ConfigurationSetting {

	public static ConfigurationSetting of(String identifier, String value) {
		return new ConfigurationSetting(identifier, value);
	}

	private String identifier;
	private String value;

}
