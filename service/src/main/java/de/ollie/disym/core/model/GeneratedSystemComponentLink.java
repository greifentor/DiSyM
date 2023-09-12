package de.ollie.disym.core.model;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A model for system_component_links.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
public class GeneratedSystemComponentLink {

	public static final String ID = "ID";
	public static final String SYSTEMCOMPONENTID = "SYSTEMCOMPONENTID";
	public static final String USES = "USES";

	private Long id;
	private SystemComponent systemComponentId;
	private SystemComponent uses;

}