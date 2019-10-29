package br.ufpa.labes.spm.web.rest;

import br.ufpa.labes.spm.domain.Process;
import br.ufpa.labes.spm.domain.ProcessModel;
import br.ufpa.labes.spm.repository.ProcessModelRepository;
import br.ufpa.labes.spm.repository.ProcessRepository;
import br.ufpa.labes.spm.service.dto.ActivitysDTO;
import br.ufpa.labes.spm.service.dto.ProcessesDTO;
import br.ufpa.labes.spm.service.interfaces.ProcessServices;
import br.ufpa.labes.spm.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/** REST controller for managing {@link br.ufpa.labes.spm.domain.Process}. */
@RestController
@RequestMapping("/api")
public class ProcessResource {

  private final Logger log = LoggerFactory.getLogger(ProcessResource.class);

  private static final String ENTITY_NAME = "process";

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  @Autowired private ProcessServices processServices;

  @Autowired private ProcessModelRepository processModelRepository;

  private final ProcessRepository processRepository;

  public ProcessResource(ProcessRepository processRepository) {
    this.processRepository = processRepository;
  }

  /**
   * {@code POST /processes} : Create a new process.
   *
   * @param process the process to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new
   *     process, or with status {@code 400 (Bad Request)} if the process has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/processes")
  public ResponseEntity<Process> createProcess(@Valid @RequestBody Process process)
      throws URISyntaxException {
    log.debug("REST request to save Process : {}", process);
    if (process.getId() != null) {
      throw new BadRequestAlertException(
          "A new process cannot already have an ID", ENTITY_NAME, "idexists");
    }
    ProcessModel pm = process.getTheProcessModel();
    if (pm == null) {
      pm = processModelRepository.save(new ProcessModel());
    }
    Process result = processRepository.save(process.theProcessModel(pm));
    return ResponseEntity.created(new URI("/api/processes/" + result.getId()))
        .headers(
            HeaderUtil.createEntityCreationAlert(
                applicationName, true, ENTITY_NAME, result.getId().toString()))
        .body(result);
  }

  /**
   * {@code PUT /processes} : Updates an existing process.
   *
   * @param process the process to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated
   *     process, or with status {@code 400 (Bad Request)} if the process is not valid, or with
   *     status {@code 500 (Internal Server Error)} if the process couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/processes")
  public ResponseEntity<Process> updateProcess(@Valid @RequestBody Process process)
      throws URISyntaxException {
    log.debug("REST request to update Process : {}", process);
    if (process.getId() == null) {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    Process result = processRepository.save(process);
    return ResponseEntity.ok()
        .headers(
            HeaderUtil.createEntityUpdateAlert(
                applicationName, true, ENTITY_NAME, process.getId().toString()))
        .body(result);
  }

  /**
   * {@code GET /processes} : get all the processes.
   *
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of processes in
   *     body.
   */
  @GetMapping("/processes")
  public List<Process> getAllProcesses() {
    log.debug("REST request to get all Processes");
    return processRepository.findAll();
  }

  /**
   * {@code GET /processes/:id} : get the "id" process.
   *
   * @param id the id of the process to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the process, or
   *     with status {@code 404 (Not Found)}.
   */
  @GetMapping("/processes/{id}")
  public ResponseEntity<Process> getProcess(@PathVariable Long id) {
    log.debug("REST request to get Process : {}", id);
    Optional<Process> process = processRepository.findById(id);
    return ResponseUtil.wrapOrNotFound(process);
  }

  // @GetMapping("/processes/projectsForAgent/{agentIdent}")
  // public ResponseEntity<ProjectsDTO> getProjectsForAgent(@PathVariable String agentIdent) {
  //   return ResponseEntity.ok().body(processServices.getProjectsForAgent(agentIdent));
  // }

  // @GetMapping("/processes/agent/{agentIdent}")
  // public ResponseEntity<List<ProcessDTO>> getProcessesByAgentId(@PathVariable String agentIdent)
  // {
  //   return ResponseEntity.ok().body(processServices.getProcess(agentIdent));
  // }

  @GetMapping("/processes/activitiesFromProcess/{processIdent}")
  public ResponseEntity<ActivitysDTO> getActivitiesFromProcess(@PathVariable String processIdent) {
    return ResponseEntity.ok().body(processServices.getActivitiesFromProcess(processIdent));
  }

  @GetMapping("/processes/managedBy/{agentIdent}")
  public ResponseEntity<ProcessesDTO> getProjectsManagedBy(@PathVariable String agentIdent) {
    return ResponseEntity.ok().body(processServices.getProjectsManagedBy(agentIdent));
  }

  /**
   * {@code DELETE /processes/:id} : delete the "id" process.
   *
   * @param id the id of the process to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/processes/{id}")
  public ResponseEntity<Void> deleteProcess(@PathVariable Long id) {
    log.debug("REST request to delete Process : {}", id);
    processRepository.deleteById(id);
    return ResponseEntity.noContent()
        .headers(
            HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
        .build();
  }
}
