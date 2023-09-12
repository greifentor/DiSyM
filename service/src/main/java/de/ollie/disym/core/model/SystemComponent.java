package de.ollie.disym.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * A model for system_components.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Generated
@ToString(callSuper = true)
public class SystemComponent extends GeneratedSystemComponent {

	@Override
	public SystemComponent setId(Long id) {
		super.setId(id);
		return this;
	}

	@Override
	public SystemComponent setName(String name) {
		super.setName(name);
		return this;
	}

}