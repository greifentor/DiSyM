package de.ollie.disym.persistence.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import lombok.Generated;
import lombok.RequiredArgsConstructor;

import de.ollie.disym.persistence.entity.SystemComponentLinkDBO;
import de.ollie.disym.core.model.SystemComponentLink;

/**
 * A DBO converter for system_component_links.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
@RequiredArgsConstructor
public class SystemComponentLinkDBOConverter implements ToModelConverter<SystemComponentLink, SystemComponentLinkDBO> {

	private final SystemComponentDBOConverter systemComponentDBOConverter;

	public SystemComponentLinkDBO toDBO(SystemComponentLink model) {
		if (model == null) {
			return null;
		}
		return new SystemComponentLinkDBO()
				.setId(model.getId())
				.setSystemComponentId(systemComponentDBOConverter.toDBO(model.getSystemComponentId()))
				.setUses(systemComponentDBOConverter.toDBO(model.getUses()));
	}

	@Override
	public SystemComponentLink toModel(SystemComponentLinkDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new SystemComponentLink()
				.setId(dbo.getId())
				.setSystemComponentId(systemComponentDBOConverter.toModel(dbo.getSystemComponentId()))
				.setUses(systemComponentDBOConverter.toModel(dbo.getUses()));
	}

	@Override
	public List<SystemComponentLink> toModel(List<SystemComponentLinkDBO> dbos) {
		if (dbos == null) {
			return null;
		}
		return dbos.stream().map(this::toModel).collect(Collectors.toList());
	}

}