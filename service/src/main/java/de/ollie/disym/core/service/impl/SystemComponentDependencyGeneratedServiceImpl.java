package de.ollie.disym.core.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponentDependency;
import de.ollie.disym.core.service.port.persistence.SystemComponentDependencyPersistencePort;
import de.ollie.disym.core.service.SystemComponentDependencyService;
import lombok.Generated;

/**
 * A generated service interface implementation for SystemComponentDependency management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class SystemComponentDependencyGeneratedServiceImpl implements SystemComponentDependencyService {

	@Inject
	protected SystemComponentDependencyPersistencePort persistencePort;

	@Override
	public SystemComponentDependency create(SystemComponentDependency model) {
		return persistencePort.create(model);
	}

	@Override
	public List<SystemComponentDependency> findAll() {
		return persistencePort.findAll();
	}

	@Override
	public Page<SystemComponentDependency> findAll(PageParameters pageParameters) {
		return persistencePort.findAll(pageParameters);
	}

	@Override
	public Optional<SystemComponentDependency> findById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public SystemComponentDependency update(SystemComponentDependency model) {
		return persistencePort.update(model);
	}

	@Override
	public void delete(SystemComponentDependency model) {
		persistencePort.delete(model);
	}

}