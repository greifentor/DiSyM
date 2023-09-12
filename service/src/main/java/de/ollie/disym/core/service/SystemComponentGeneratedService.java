package de.ollie.disym.core.service;

import java.util.List;
import java.util.Optional;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponent;
import lombok.Generated;

/**
 * A generated service interface for SystemComponent management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SystemComponentGeneratedService {

	SystemComponent create(SystemComponent model);

	List<SystemComponent> findAll();

	Page<SystemComponent> findAll(PageParameters pageParameters);

	Optional<SystemComponent> findById(Long id);

	SystemComponent update(SystemComponent model);

	void delete(SystemComponent model);

}