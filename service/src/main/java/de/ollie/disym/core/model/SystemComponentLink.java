package de.ollie.disym.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * A model for system_component_links.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Generated
@ToString(callSuper = true)
public class SystemComponentLink extends GeneratedSystemComponentLink {

	@Override
	public SystemComponentLink setId(Long id) {
		super.setId(id);
		return this;
	}

	@Override
	public SystemComponentLink setSystemComponentId(SystemComponent systemComponentId) {
		super.setSystemComponentId(systemComponentId);
		return this;
	}

	@Override
	public SystemComponentLink setUses(SystemComponent uses) {
		super.setUses(uses);
		return this;
	}

}