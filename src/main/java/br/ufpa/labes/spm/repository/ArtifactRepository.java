package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.Artifact;
import br.ufpa.labes.spm.repository.interfaces.ArtifactRepositoryQuery;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;

/** Spring Data repository for the Artifact entity. */
@SuppressWarnings("unused")
public interface ArtifactRepository
    extends GenericRepository<Artifact, Long>, ArtifactRepositoryQuery {}
