package br.ufpa.labes.spm.web.rest;

import br.ufpa.labes.spm.domain.Dependency;
import br.ufpa.labes.spm.repository.DependencyRepository;
import br.ufpa.labes.spm.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/** REST controller for managing {@link br.ufpa.labes.spm.domain.Dependency}. */
@RestController
@RequestMapping("/api")
public class DependencyResource {

  private final Logger log = LoggerFactory.getLogger(DependencyResource.class);

  private static final String ENTITY_NAME = "dependency";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final DependencyRepository dependencyRepository;

  public DependencyResource(DependencyRepository dependencyRepository) {
    this.dependencyRepository = dependencyRepository;
  }

  /**
   * {@code POST /dependencies} : Create a new dependency.
   *
   * @param dependency the dependency to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
   *     dependency, or with status {@code 400 (Bad Request)} if the dependency has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/dependencies")
  public ResponseEntity<Dependency> createDependency(@RequestBody Dependency dependency)
      throws URISyntaxException {
    log.debug("REST request to save Dependency : {}", dependency);
    if (dependency.getId() != null) {
      throw new BadRequestAlertException(
          "A new dependency cannot already have an ID", ENTITY_NAME, "idexists");
    }
    Dependency result = dependencyRepository.save(dependency);
    return ResponseEntity.created(new URI("/api/dependencies/" + result.getId()))
        .headers(
            HeaderUtil.createEntityCreationAlert(
                applicationName, true, ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * {@code PUT /dependencies} : Updates an existing dependency.
   *
   * @param dependency the dependency to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated
   *     dependency, or with status {@code 400 (Bad Request)} if the dependency is not valid, or
   *     with status {@code 500 (Internal Server Error)} if the dependency couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/dependencies")
  public ResponseEntity<Dependency> updateDependency(@RequestBody Dependency dependency)
      throws URISyntaxException {
    log.debug("REST request to update Dependency : {}", dependency);
    if (dependency.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    Dependency result = dependencyRepository.save(dependency);
    return ResponseEntity.ok()
        .headers(
            HeaderUtil.createEntityUpdateAlert(
                applicationName, true, ENTITY_NAME, dependency.getId().toString()))
        .body(result);
  }

  /**
   * {@code GET /dependencies} : get all the dependencies.
   *
   * @param filter the filter of the request.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dependencies in
   *     body.
   */
  @GetMapping("/dependencies")
  public List<Dependency> getAllDependencies(@RequestParam(required = false) String filter) {
    if ("themultiplecon-is-null".equals(filter)) {
      log.debug("REST request to get all Dependencys where theMultipleCon is null");
      return StreamSupport.stream(dependencyRepository.findAll().spliterator(), false)
          .filter(dependency -> dependency.getTheMultipleCon() == null)
          .collect(Collectors.toList());
    }
    log.debug("REST request to get all Dependencies");
    return dependencyRepository.findAll();
  }

  /**
   * {@code GET /dependencies/:id} : get the "id" dependency.
   *
   * @param id the id of the dependency to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dependency,
   *     or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/dependencies/{id}")
  public ResponseEntity<Dependency> getDependency(@PathVariable Long id) {
    log.debug("REST request to get Dependency : {}", id);
    Optional<Dependency> dependency = dependencyRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(dependency);
  }

  /**
   * {@code DELETE /dependencies/:id} : delete the "id" dependency.
   *
   * @param id the id of the dependency to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/dependencies/{id}")
  public ResponseEntity<Void> deleteDependency(@PathVariable Long id) {
    log.debug("REST request to delete Dependency : {}", id);
    dependencyRepository.deleteById(id);
    return ResponseEntity.noContent()
        .headers(
            HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }
}
