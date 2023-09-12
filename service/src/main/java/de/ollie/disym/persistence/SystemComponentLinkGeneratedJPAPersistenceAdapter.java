package de.ollie.disym.persistence;

import static de.ollie.disym.util.Check.ensure;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponentLink;
import de.ollie.disym.core.service.exception.NotNullConstraintViolationException;
import de.ollie.disym.core.service.port.persistence.SystemComponentLinkPersistencePort;
import de.ollie.disym.persistence.converter.PageConverter;
import de.ollie.disym.persistence.converter.PageParametersToPageableConverter;
import de.ollie.disym.persistence.converter.SystemComponentLinkDBOConverter;
import de.ollie.disym.persistence.entity.SystemComponentLinkDBO;
import de.ollie.disym.persistence.repository.SystemComponentLinkDBORepository;
import lombok.Generated;

/**
 * A generated JPA persistence adapter for system_component_links.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class SystemComponentLinkGeneratedJPAPersistenceAdapter implements SystemComponentLinkPersistencePort {

	@Inject
	protected SystemComponentLinkDBOConverter converter;
	@Inject
	protected SystemComponentLinkDBORepository repository;

	@Inject
	protected PageParametersToPageableConverter pageParametersToPageableConverter;

	protected PageConverter<SystemComponentLink, SystemComponentLinkDBO> pageConverter;

	@PostConstruct
	public void postConstruct() {
		pageConverter = new PageConverter<>(converter);
	}

	@Override
	public SystemComponentLink create(SystemComponentLink model) {
		model.setId(null);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public List<SystemComponentLink> findAll() {
		return converter.toModel(repository.findAll());
	}

	@Override
	public Page<SystemComponentLink> findAll(PageParameters pageParameters) {
		return pageConverter.convert(repository.findAll(pageParametersToPageableConverter.convert(pageParameters)));
	}

	@Override
	public Optional<SystemComponentLink> findById(Long id) {
		return repository.findById(id).map(dbo -> converter.toModel(dbo));
	}

	@Override
	public SystemComponentLink update(SystemComponentLink model) {
		ensure(
				model.getSystemComponentId() != null,
				() -> new NotNullConstraintViolationException("SystemComponentLink field systemComponentId cannot be null.", "SystemComponentLink", "systemComponentId"));
		ensure(
				model.getUses() != null,
				() -> new NotNullConstraintViolationException("SystemComponentLink field uses cannot be null.", "SystemComponentLink", "uses"));
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public void delete(SystemComponentLink model) {
		repository.deleteById(model.getId());
	}

}