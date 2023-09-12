package de.ollie.disym.core.service.port.persistence;

import java.util.List;
import java.util.Optional;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponentDependency;
import lombok.Generated;

/**
 * A generated persistence port interface for SystemComponentDependency CRUD operations.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SystemComponentDependencyGeneratedPersistencePort {

	SystemComponentDependency create(SystemComponentDependency model);

	List<SystemComponentDependency> findAll();

	Page<SystemComponentDependency> findAll(PageParameters pageParameters);

	Optional<SystemComponentDependency> findById(Long id);

	SystemComponentDependency update(SystemComponentDependency model);

	void delete(SystemComponentDependency model);

}