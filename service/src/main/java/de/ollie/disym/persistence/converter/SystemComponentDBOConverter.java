package de.ollie.disym.persistence.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.disym.persistence.entity.SystemComponentDBO;
import de.ollie.disym.core.model.SystemComponent;

/**
 * A DBO converter for system_components.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class SystemComponentDBOConverter implements ToModelConverter<SystemComponent, SystemComponentDBO> {

	public SystemComponentDBO toDBO(SystemComponent model) {
		if (model == null) {
			return null;
		}
		return new SystemComponentDBO()
				.setId(model.getId())
				.setName(model.getName());
	}

	@Override
	public SystemComponent toModel(SystemComponentDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new SystemComponent()
				.setId(dbo.getId())
				.setName(dbo.getName());
	}

	@Override
	public List<SystemComponent> toModel(List<SystemComponentDBO> dbos) {
		if (dbos == null) {
			return null;
		}
		return dbos.stream().map(this::toModel).collect(Collectors.toList());
	}

}