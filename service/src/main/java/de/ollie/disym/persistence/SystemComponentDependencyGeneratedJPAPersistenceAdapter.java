package de.ollie.disym.persistence;

import static de.ollie.disym.util.Check.ensure;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponentDependency;
import de.ollie.disym.core.service.exception.NotNullConstraintViolationException;
import de.ollie.disym.core.service.port.persistence.SystemComponentDependencyPersistencePort;
import de.ollie.disym.persistence.converter.PageConverter;
import de.ollie.disym.persistence.converter.PageParametersToPageableConverter;
import de.ollie.disym.persistence.converter.SystemComponentDependencyDBOConverter;
import de.ollie.disym.persistence.entity.SystemComponentDependencyDBO;
import de.ollie.disym.persistence.repository.SystemComponentDependencyDBORepository;
import lombok.Generated;

/**
 * A generated JPA persistence adapter for system_component_dependencys.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class SystemComponentDependencyGeneratedJPAPersistenceAdapter implements SystemComponentDependencyPersistencePort {

	@Inject
	protected SystemComponentDependencyDBOConverter converter;
	@Inject
	protected SystemComponentDependencyDBORepository repository;

	@Inject
	protected PageParametersToPageableConverter pageParametersToPageableConverter;

	protected PageConverter<SystemComponentDependency, SystemComponentDependencyDBO> pageConverter;

	@PostConstruct
	public void postConstruct() {
		pageConverter = new PageConverter<>(converter);
	}

	@Override
	public SystemComponentDependency create(SystemComponentDependency model) {
		model.setId(null);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public List<SystemComponentDependency> findAll() {
		return converter.toModel(repository.findAll());
	}

	@Override
	public Page<SystemComponentDependency> findAll(PageParameters pageParameters) {
		return pageConverter.convert(repository.findAll(pageParametersToPageableConverter.convert(pageParameters)));
	}

	@Override
	public Optional<SystemComponentDependency> findById(Long id) {
		return repository.findById(id).map(dbo -> converter.toModel(dbo));
	}

	@Override
	public SystemComponentDependency update(SystemComponentDependency model) {
		ensure(
				model.getDependencyId() != null,
				() -> new NotNullConstraintViolationException("SystemComponentDependency field dependencyId cannot be null.", "SystemComponentDependency", "dependencyId"));
		ensure(
				model.getSystemComponentId() != null,
				() -> new NotNullConstraintViolationException("SystemComponentDependency field systemComponentId cannot be null.", "SystemComponentDependency", "systemComponentId"));
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public void delete(SystemComponentDependency model) {
		repository.deleteById(model.getId());
	}

}