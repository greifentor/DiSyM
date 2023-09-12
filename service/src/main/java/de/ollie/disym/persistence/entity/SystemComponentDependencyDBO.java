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
 * A DBO for system_component_dependencys.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "SystemComponentDependency")
@Table(name = "SYSTEM_COMPONENT_DEPENDENCY")
public class SystemComponentDependencyDBO {

	@Id
	@Column(name = "ID")
	private Long id;
	@JoinColumn(name = "DEPENDENCY_ID", nullable = false, referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private DependencyDBO dependencyId;
	@JoinColumn(name = "SYSTEM_COMPONENT_ID", nullable = false, referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private SystemComponentDBO systemComponentId;

}