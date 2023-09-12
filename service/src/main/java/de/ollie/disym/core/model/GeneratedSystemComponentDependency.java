package de.ollie.disym.core.model;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A model for system_component_dependencys.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
public class GeneratedSystemComponentDependency {

	public static final String ID = "ID";
	public static final String DEPENDENCYID = "DEPENDENCYID";
	public static final String SYSTEMCOMPONENTID = "SYSTEMCOMPONENTID";

	private Long id;
	private Dependency dependencyId;
	private SystemComponent systemComponentId;

}