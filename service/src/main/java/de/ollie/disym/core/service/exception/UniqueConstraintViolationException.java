package de.ollie.disym.core.service.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Generated;

@Generated
public class UniqueConstraintViolationException extends RuntimeException {

	private List<String> attributeNames = new ArrayList<>();
	private String className;

	public UniqueConstraintViolationException(String message, String className, String... attributeNames) {
		super(message);
		this.className = className;
		for (String attributeName : attributeNames) {
			if ((attributeName != null) && !this.attributeNames.contains(attributeName)) {
				this.attributeNames.add(attributeName);
			}
		}
	}

	public List<String> getAttributeNames() {
		return List.copyOf(attributeNames);
	}

	public String getClassName() {
		return className;
	}

}