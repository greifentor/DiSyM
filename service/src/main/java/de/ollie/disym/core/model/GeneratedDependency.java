package de.ollie.disym.core.model;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A model for dependencys.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
public class GeneratedDependency {

	public static final String ID = "ID";
	public static final String SYSTEMCOMPONENT = "SYSTEMCOMPONENT";
	public static final String ARTEFACTID = "ARTEFACTID";
	public static final String GROUPID = "GROUPID";
	public static final String VERSION = "VERSION";

	private Long id;
	private SystemComponent systemComponent;
	private String artefactId;
	private String groupId;
	private String version;

}