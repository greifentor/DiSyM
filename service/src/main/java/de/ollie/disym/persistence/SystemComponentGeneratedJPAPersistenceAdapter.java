package de.ollie.disym.persistence;

import static de.ollie.disym.util.Check.ensure;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponent;
import de.ollie.disym.core.service.exception.NotNullConstraintViolationException;
import de.ollie.disym.core.service.port.persistence.SystemComponentPersistencePort;
import de.ollie.disym.persistence.converter.PageConverter;
import de.ollie.disym.persistence.converter.PageParametersToPageableConverter;
import de.ollie.disym.persistence.converter.SystemComponentDBOConverter;
import de.ollie.disym.persistence.entity.SystemComponentDBO;
import de.ollie.disym.persistence.repository.SystemComponentDBORepository;
import lombok.Generated;

/**
 * A generated JPA persistence adapter for system_components.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class SystemComponentGeneratedJPAPersistenceAdapter implements SystemComponentPersistencePort {

	@Inject
	protected SystemComponentDBOConverter converter;
	@Inject
	protected SystemComponentDBORepository repository;

	@Inject
	protected PageParametersToPageableConverter pageParametersToPageableConverter;

	protected PageConverter<SystemComponent, SystemComponentDBO> pageConverter;

	@PostConstruct
	public void postConstruct() {
		pageConverter = new PageConverter<>(converter);
	}

	@Override
	public SystemComponent create(SystemComponent model) {
		model.setId(null);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public List<SystemComponent> findAll() {
		return converter.toModel(repository.findAll());
	}

	@Override
	public Page<SystemComponent> findAll(PageParameters pageParameters) {
		return pageConverter.convert(repository.findAll(pageParametersToPageableConverter.convert(pageParameters)));
	}

	@Override
	public Optional<SystemComponent> findById(Long id) {
		return repository.findById(id).map(dbo -> converter.toModel(dbo));
	}

	@Override
	public SystemComponent update(SystemComponent model) {
		ensure(
				model.getName() != null,
				() -> new NotNullConstraintViolationException("SystemComponent field name cannot be null.", "SystemComponent", "name"));
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public void delete(SystemComponent model) {
		repository.deleteById(model.getId());
	}

}