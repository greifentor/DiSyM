package de.ollie.disym.core.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.Dependency;
import de.ollie.disym.core.service.port.persistence.DependencyPersistencePort;
import de.ollie.disym.core.service.DependencyService;
import lombok.Generated;

/**
 * A generated service interface implementation for Dependency management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class DependencyGeneratedServiceImpl implements DependencyService {

	@Inject
	protected DependencyPersistencePort persistencePort;

	@Override
	public Dependency create(Dependency model) {
		return persistencePort.create(model);
	}

	@Override
	public List<Dependency> findAll() {
		return persistencePort.findAll();
	}

	@Override
	public Page<Dependency> findAll(PageParameters pageParameters) {
		return persistencePort.findAll(pageParameters);
	}

	@Override
	public Optional<Dependency> findById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public Dependency update(Dependency model) {
		return persistencePort.update(model);
	}

	@Override
	public void delete(Dependency model) {
		persistencePort.delete(model);
	}

}