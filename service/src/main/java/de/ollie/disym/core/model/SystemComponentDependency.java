package de.ollie.disym.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * A model for system_component_dependencys.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Generated
@ToString(callSuper = true)
public class SystemComponentDependency extends GeneratedSystemComponentDependency {

	@Override
	public SystemComponentDependency setId(Long id) {
		super.setId(id);
		return this;
	}

	@Override
	public SystemComponentDependency setDependencyId(Dependency dependencyId) {
		super.setDependencyId(dependencyId);
		return this;
	}

	@Override
	public SystemComponentDependency setSystemComponentId(SystemComponent systemComponentId) {
		super.setSystemComponentId(systemComponentId);
		return this;
	}

}