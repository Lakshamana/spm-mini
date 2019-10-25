package br.ufpa.labes.spm.repository;
import br.ufpa.labes.spm.domain.Process;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;
import br.ufpa.labes.spm.repository.interfaces.ProcessRepositoryQuery;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Process entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProcessRepository extends GenericRepository<Process, Long>, ProcessRepositoryQuery {

}
