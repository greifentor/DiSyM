package de.ollie.disym.service.model.rule;

import java.util.List;

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
public class Rule {

	public static Rule of(List<Word> words) {
		return new Rule(words);
	}

	private List<Word> words;

}
