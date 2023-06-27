package de.ollie.disym.service.model.rule;

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
public class Value implements Word {

	private Object value;

	public static Value of(Object value) {
		return new Value(value);
	}

}
