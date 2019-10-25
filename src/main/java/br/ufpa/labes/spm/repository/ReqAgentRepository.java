package br.ufpa.labes.spm.repository;
import br.ufpa.labes.spm.domain.ReqAgent;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;
import br.ufpa.labes.spm.repository.interfaces.ReqAgentRepositoryQuery;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReqAgent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReqAgentRepository extends GenericRepository<ReqAgent, Long>, ReqAgentRepositoryQuery {

}
