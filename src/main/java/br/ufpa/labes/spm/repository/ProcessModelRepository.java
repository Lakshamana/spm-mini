package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.ProcessModel;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/** Spring Data repository for the ProcessModel entity. */
@SuppressWarnings("unused")
@Repository
public interface ProcessModelRepository extends GenericRepository<ProcessModel, Long> {
  @Query("select pm from ProcessModel pm left join fetch pm.theActivities left join fetch pm.theConnections where pm.id = :id")
  ProcessModel findOneWithEagerRelationshipsById(@Param("id") Long id);
}
