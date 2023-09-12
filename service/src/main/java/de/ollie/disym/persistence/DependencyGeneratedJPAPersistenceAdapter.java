package de.ollie.disym.persistence;

import static de.ollie.disym.util.Check.ensure;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.Dependency;
import de.ollie.disym.core.service.exception.NotNullConstraintViolationException;
import de.ollie.disym.core.service.port.persistence.DependencyPersistencePort;
import de.ollie.disym.persistence.converter.PageConverter;
import de.ollie.disym.persistence.converter.PageParametersToPageableConverter;
import de.ollie.disym.persistence.converter.DependencyDBOConverter;
import de.ollie.disym.persistence.entity.DependencyDBO;
import de.ollie.disym.persistence.repository.DependencyDBORepository;
import lombok.Generated;

/**
 * A generated JPA persistence adapter for dependencys.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class DependencyGeneratedJPAPersistenceAdapter implements DependencyPersistencePort {

	@Inject
	protected DependencyDBOConverter converter;
	@Inject
	protected DependencyDBORepository repository;

	@Inject
	protected PageParametersToPageableConverter pageParametersToPageableConverter;

	protected PageConverter<Dependency, DependencyDBO> pageConverter;

	@PostConstruct
	public void postConstruct() {
		pageConverter = new PageConverter<>(converter);
	}

	@Override
	public Dependency create(Dependency model) {
		model.setId(null);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public List<Dependency> findAll() {
		return converter.toModel(repository.findAll());
	}

	@Override
	public Page<Dependency> findAll(PageParameters pageParameters) {
		return pageConverter.convert(repository.findAll(pageParametersToPageableConverter.convert(pageParameters)));
	}

	@Override
	public Optional<Dependency> findById(Long id) {
		return repository.findById(id).map(dbo -> converter.toModel(dbo));
	}

	@Override
	public Dependency update(Dependency model) {
		ensure(
				model.getArtefactId() != null,
				() -> new NotNullConstraintViolationException("Dependency field artefactId cannot be null.", "Dependency", "artefactId"));
		ensure(
				model.getGroupId() != null,
				() -> new NotNullConstraintViolationException("Dependency field groupId cannot be null.", "Dependency", "groupId"));
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public void delete(Dependency model) {
		repository.deleteById(model.getId());
	}

}