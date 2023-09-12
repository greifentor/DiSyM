package de.ollie.disym.core.service;

import java.util.List;
import java.util.Optional;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.Dependency;
import lombok.Generated;

/**
 * A generated service interface for Dependency management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface DependencyGeneratedService {

	Dependency create(Dependency model);

	List<Dependency> findAll();

	Page<Dependency> findAll(PageParameters pageParameters);

	Optional<Dependency> findById(Long id);

	Dependency update(Dependency model);

	void delete(Dependency model);

}