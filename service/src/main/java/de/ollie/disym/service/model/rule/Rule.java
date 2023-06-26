package de.ollie.disym.service.model.rule;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Generated
@Getter
@ToString
public class Rule {

	private String name;
	private List<Word> words;

}
