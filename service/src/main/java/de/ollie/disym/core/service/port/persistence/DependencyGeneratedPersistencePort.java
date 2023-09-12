package de.ollie.disym.core.service.port.persistence;

import java.util.List;
import java.util.Optional;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.Dependency;
import lombok.Generated;

/**
 * A generated persistence port interface for Dependency CRUD operations.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface DependencyGeneratedPersistencePort {

	Dependency create(Dependency model);

	List<Dependency> findAll();

	Page<Dependency> findAll(PageParameters pageParameters);

	Optional<Dependency> findById(Long id);

	Dependency update(Dependency model);

	void delete(Dependency model);

}