package de.ollie.disym.persistence.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

import de.ollie.disym.persistence.entity.DependencyDBO;
import de.ollie.disym.core.model.Dependency;

/**
 * A DBO converter for dependencys.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
@RequiredArgsConstructor
public class DependencyDBOConverter implements ToModelConverter<Dependency, DependencyDBO> {

	private final SystemComponentDBOConverter systemComponentDBOConverter;

	public DependencyDBO toDBO(Dependency model) {
		if (model == null) {
			return null;
		}
		return new DependencyDBO()
				.setId(model.getId())
				.setSystemComponent(systemComponentDBOConverter.toDBO(model.getSystemComponent()))
				.setArtefactId(model.getArtefactId())
				.setGroupId(model.getGroupId())
				.setVersion(model.getVersion());
	}

	@Override
	public Dependency toModel(DependencyDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new Dependency()
				.setId(dbo.getId())
				.setSystemComponent(systemComponentDBOConverter.toModel(dbo.getSystemComponent()))
				.setArtefactId(dbo.getArtefactId())
				.setGroupId(dbo.getGroupId())
				.setVersion(dbo.getVersion());
	}

	@Override
	public List<Dependency> toModel(List<DependencyDBO> dbos) {
		if (dbos == null) {
			return null;
		}
		return dbos.stream().map(this::toModel).collect(Collectors.toList());
	}

}