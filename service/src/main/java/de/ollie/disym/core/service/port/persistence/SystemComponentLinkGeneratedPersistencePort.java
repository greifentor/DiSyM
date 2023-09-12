package de.ollie.disym.core.service.port.persistence;

import java.util.List;
import java.util.Optional;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponentLink;
import lombok.Generated;

/**
 * A generated persistence port interface for SystemComponentLink CRUD operations.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SystemComponentLinkGeneratedPersistencePort {

	SystemComponentLink create(SystemComponentLink model);

	List<SystemComponentLink> findAll();

	Page<SystemComponentLink> findAll(PageParameters pageParameters);

	Optional<SystemComponentLink> findById(Long id);

	SystemComponentLink update(SystemComponentLink model);

	void delete(SystemComponentLink model);

}