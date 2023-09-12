package de.ollie.disym.core.service;

import java.util.List;
import java.util.Optional;

import de.ollie.disym.core.model.Page;
import de.ollie.disym.core.model.PageParameters;
import de.ollie.disym.core.model.SystemComponentLink;
import lombok.Generated;

/**
 * A generated service interface for SystemComponentLink management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface SystemComponentLinkGeneratedService {

	SystemComponentLink create(SystemComponentLink model);

	List<SystemComponentLink> findAll();

	Page<SystemComponentLink> findAll(PageParameters pageParameters);

	Optional<SystemComponentLink> findById(Long id);

	SystemComponentLink update(SystemComponentLink model);

	void delete(SystemComponentLink model);

}