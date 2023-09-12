package de.ollie.disym.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A DBO for system_components.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "SystemComponent")
@Table(name = "SYSTEM_COMPONENT")
public class SystemComponentDBO {

	@Id
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME", nullable = false)
	private String name;

}