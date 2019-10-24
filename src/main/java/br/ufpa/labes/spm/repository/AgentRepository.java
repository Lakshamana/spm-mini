package br.ufpa.labes.spm.repository;
import br.ufpa.labes.spm.domain.Agent;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Agent entity.
 */
@Repository
public interface AgentRepository extends GenericRepository<Agent, Long> {

    @Query(value = "select distinct agent from Agent agent left join fetch agent.theProcesses left join fetch agent.theWorkGroups",
        countQuery = "select count(distinct agent) from Agent agent")
    Page<Agent> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct agent from Agent agent left join fetch agent.theProcesses left join fetch agent.theWorkGroups")
    List<Agent> findAllWithEagerRelationships();

    @Query("select agent from Agent agent left join fetch agent.theProcesses left join fetch agent.theWorkGroups where agent.id =:id")
    Optional<Agent> findOneWithEagerRelationships(@Param("id") Long id);

}
