package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.Project;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/** Spring Data repository for the Project entity. */
@SuppressWarnings("unused")
@Repository
public interface ProjectRepository extends GenericRepository<Project, Long> {}
