package de.ollie.disym.core.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponent;
import de.ollie.disym.core.service.port.persistence.SystemComponentPersistencePort;
import de.ollie.disym.core.service.SystemComponentService;
import lombok.Generated;

/**
 * A generated service interface implementation for SystemComponent management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class SystemComponentGeneratedServiceImpl implements SystemComponentService {

	@Inject
	protected SystemComponentPersistencePort persistencePort;

	@Override
	public SystemComponent create(SystemComponent model) {
		return persistencePort.create(model);
	}

	@Override
	public List<SystemComponent> findAll() {
		return persistencePort.findAll();
	}

	@Override
	public Page<SystemComponent> findAll(PageParameters pageParameters) {
		return persistencePort.findAll(pageParameters);
	}

	@Override
	public Optional<SystemComponent> findById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public SystemComponent update(SystemComponent model) {
		return persistencePort.update(model);
	}

	@Override
	public void delete(SystemComponent model) {
		persistencePort.delete(model);
	}

}