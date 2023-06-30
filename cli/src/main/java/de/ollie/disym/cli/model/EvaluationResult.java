package de.ollie.disym.cli.model;

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
public class EvaluationResult {

	public static EvaluationResult of(String configurationSettingName, boolean result) {
		return new EvaluationResult(configurationSettingName, result);
	}

	private String configurationSettingName;
	private boolean result;

}
