package de.ollie.disym.core.service;

import java.util.List;
import java.util.Optional;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponentDependency;
import lombok.Generated;

/**
 * A generated service interface for SystemComponentDependency management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SystemComponentDependencyGeneratedService {

	SystemComponentDependency create(SystemComponentDependency model);

	List<SystemComponentDependency> findAll();

	Page<SystemComponentDependency> findAll(PageParameters pageParameters);

	Optional<SystemComponentDependency> findById(Long id);

	SystemComponentDependency update(SystemComponentDependency model);

	void delete(SystemComponentDependency model);

}