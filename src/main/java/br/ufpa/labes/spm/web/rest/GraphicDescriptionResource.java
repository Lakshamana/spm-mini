package br.ufpa.labes.spm.web.rest;

import br.ufpa.labes.spm.domain.GraphicDescription;
import br.ufpa.labes.spm.repository.GraphicDescriptionRepository;
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

/** REST controller for managing {@link br.ufpa.labes.spm.domain.GraphicDescription}. */
@RestController
@RequestMapping("/api")
public class GraphicDescriptionResource {

  private final Logger log = LoggerFactory.getLogger(GraphicDescriptionResource.class);

  private static final String ENTITY_NAME = "graphicDescription";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  private final GraphicDescriptionRepository graphicDescriptionRepository;

  public GraphicDescriptionResource(GraphicDescriptionRepository graphicDescriptionRepository) {
    this.graphicDescriptionRepository = graphicDescriptionRepository;
  }

  /**
   * {@code POST /graphic-descriptions} : Create a new graphicDescription.
   *
   * @param graphicDescription the graphicDescription to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
   *     graphicDescription, or with status {@code 400 (Bad Request)} if the graphicDescription has
   *     already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/graphic-descriptions")
  public ResponseEntity<GraphicDescription> createGraphicDescription(
      @RequestBody GraphicDescription graphicDescription) throws URISyntaxException {
    log.debug("REST request to save GraphicDescription : {}", graphicDescription);
    if (graphicDescription.getId() != null) {
      throw new BadRequestAlertException(
          "A new graphicDescription cannot already have an ID", ENTITY_NAME, "idexists");
    }
    GraphicDescription result = graphicDescriptionRepository.save(graphicDescription);
    return ResponseEntity.created(new URI("/api/graphic-descriptions/" + result.getId()))
        .headers(
            HeaderUtil.createEntityCreationAlert(
                applicationName, true, ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * {@code PUT /graphic-descriptions} : Updates an existing graphicDescription.
   *
   * @param graphicDescription the graphicDescription to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated
   *     graphicDescription, or with status {@code 400 (Bad Request)} if the graphicDescription is
   *     not valid, or with status {@code 500 (Internal Server Error)} if the graphicDescription
   *     couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/graphic-descriptions")
  public ResponseEntity<GraphicDescription> updateGraphicDescription(
      @RequestBody GraphicDescription graphicDescription) throws URISyntaxException {
    log.debug("REST request to update GraphicDescription : {}", graphicDescription);
    if (graphicDescription.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    GraphicDescription result = graphicDescriptionRepository.save(graphicDescription);
    return ResponseEntity.ok()
        .headers(
            HeaderUtil.createEntityUpdateAlert(
                applicationName, true, ENTITY_NAME, graphicDescription.getId().toString()))
        .body(result);
  }

  /**
   * {@code GET /graphic-descriptions} : get all the graphicDescriptions.
   *
   * @param filter the filter of the request.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of
   *     graphicDescriptions in body.
   */
  @GetMapping("/graphic-descriptions")
  public List<GraphicDescription> getAllGraphicDescriptions(
      @RequestParam(required = false) String filter) {
    if ("theprocessmodel-is-null".equals(filter)) {
      log.debug("REST request to get all GraphicDescriptions where theProcessModel is null");
      return StreamSupport.stream(graphicDescriptionRepository.findAll().spliterator(), false)
          .filter(graphicDescription -> graphicDescription.getTheProcessModel() == null)
          .collect(Collectors.toList());
    }
    log.debug("REST request to get all GraphicDescriptions");
    return graphicDescriptionRepository.findAll();
  }

  /**
   * {@code GET /graphic-descriptions/:id} : get the "id" graphicDescription.
   *
   * @param id the id of the graphicDescription to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the
   *     graphicDescription, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/graphic-descriptions/{id}")
  public ResponseEntity<GraphicDescription> getGraphicDescription(@PathVariable Long id) {
    log.debug("REST request to get GraphicDescription : {}", id);
    Optional<GraphicDescription> graphicDescription = graphicDescriptionRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(graphicDescription);
  }

  /**
   * {@code DELETE /graphic-descriptions/:id} : delete the "id" graphicDescription.
   *
   * @param id the id of the graphicDescription to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/graphic-descriptions/{id}")
  public ResponseEntity<Void> deleteGraphicDescription(@PathVariable Long id) {
    log.debug("REST request to delete GraphicDescription : {}", id);
    graphicDescriptionRepository.deleteById(id);
    return ResponseEntity.noContent()
        .headers(
            HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }
}
