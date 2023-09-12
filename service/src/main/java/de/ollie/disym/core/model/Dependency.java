package de.ollie.disym.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * A model for dependencys.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
@Generated
@ToString(callSuper = true)
public class Dependency extends GeneratedDependency {

	@Override
	public Dependency setId(Long id) {
		super.setId(id);
		return this;
	}

	@Override
	public Dependency setSystemComponent(SystemComponent systemComponent) {
		super.setSystemComponent(systemComponent);
		return this;
	}

	@Override
	public Dependency setArtefactId(String artefactId) {
		super.setArtefactId(artefactId);
		return this;
	}

	@Override
	public Dependency setGroupId(String groupId) {
		super.setGroupId(groupId);
		return this;
	}

	@Override
	public Dependency setVersion(String version) {
		super.setVersion(version);
		return this;
	}

}