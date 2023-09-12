package de.ollie.disym.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A DBO for dependencys.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "Dependency")
@Table(name = "DEPENDENCY")
public class DependencyDBO {

	@Id
	@Column(name = "ID")
	private Long id;
	@JoinColumn(name = "SYSTEM_COMPONENT", referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private SystemComponentDBO systemComponent;
	@Column(name = "ARTEFACT_ID", nullable = false)
	private String artefactId;
	@Column(name = "GROUP_ID", nullable = false)
	private String groupId;
	@Column(name = "VERSION")
	private String version;

}