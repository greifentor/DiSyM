package de.ollie.disym.core.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponentLink;
import de.ollie.disym.core.service.port.persistence.SystemComponentLinkPersistencePort;
import de.ollie.disym.core.service.SystemComponentLinkService;
import lombok.Generated;

/**
 * A generated service interface implementation for SystemComponentLink management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class SystemComponentLinkGeneratedServiceImpl implements SystemComponentLinkService {

	@Inject
	protected SystemComponentLinkPersistencePort persistencePort;

	@Override
	public SystemComponentLink create(SystemComponentLink model) {
		return persistencePort.create(model);
	}

	@Override
	public List<SystemComponentLink> findAll() {
		return persistencePort.findAll();
	}

	@Override
	public Page<SystemComponentLink> findAll(PageParameters pageParameters) {
		return persistencePort.findAll(pageParameters);
	}

	@Override
	public Optional<SystemComponentLink> findById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public SystemComponentLink update(SystemComponentLink model) {
		return persistencePort.update(model);
	}

	@Override
	public void delete(SystemComponentLink model) {
		persistencePort.delete(model);
	}

}