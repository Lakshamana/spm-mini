package br.ufpa.labes.spm.repository;
import br.ufpa.labes.spm.domain.Artifact;
import br.ufpa.labes.spm.repository.interfaces.ArtifactRepositoryQuery;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Artifact entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArtifactRepository extends GenericRepository<Artifact, Long>, ArtifactRepositoryQuery {

}
