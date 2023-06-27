package de.ollie.disym.service.model.rule;

import java.util.Stack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Generated
@Getter
@ToString
public abstract class Command implements Word {

	private String token;

	public abstract Stack<Object> evaluate(Stack<Object> stack);

}
