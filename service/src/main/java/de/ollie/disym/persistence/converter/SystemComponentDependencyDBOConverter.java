package de.ollie.disym.persistence.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

import de.ollie.disym.persistence.entity.SystemComponentDependencyDBO;
import de.ollie.disym.core.model.SystemComponentDependency;

/**
 * A DBO converter for system_component_dependencys.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
@RequiredArgsConstructor
public class SystemComponentDependencyDBOConverter implements ToModelConverter<SystemComponentDependency, SystemComponentDependencyDBO> {

	private final DependencyDBOConverter dependencyDBOConverter;
	private final SystemComponentDBOConverter systemComponentDBOConverter;

	public SystemComponentDependencyDBO toDBO(SystemComponentDependency model) {
		if (model == null) {
			return null;
		}
		return new SystemComponentDependencyDBO()
				.setId(model.getId())
				.setDependencyId(dependencyDBOConverter.toDBO(model.getDependencyId()))
				.setSystemComponentId(systemComponentDBOConverter.toDBO(model.getSystemComponentId()));
	}

	@Override
	public SystemComponentDependency toModel(SystemComponentDependencyDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new SystemComponentDependency()
				.setId(dbo.getId())
				.setDependencyId(dependencyDBOConverter.toModel(dbo.getDependencyId()))
				.setSystemComponentId(systemComponentDBOConverter.toModel(dbo.getSystemComponentId()));
	}

	@Override
	public List<SystemComponentDependency> toModel(List<SystemComponentDependencyDBO> dbos) {
		if (dbos == null) {
			return null;
		}
		return dbos.stream().map(this::toModel).collect(Collectors.toList());
	}

}