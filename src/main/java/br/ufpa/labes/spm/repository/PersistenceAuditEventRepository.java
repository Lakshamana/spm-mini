package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.PersistentAuditEvent;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

/** Spring Data JPA repository for the {@link PersistentAuditEvent} entity. */
public interface PersistenceAuditEventRepository
    extends GenericRepository<PersistentAuditEvent, Long> {

  List<PersistentAuditEvent> findByPrincipal(String principal);

  List<PersistentAuditEvent> findByPrincipalAndAuditEventDateAfterAndAuditEventType(
      String principal, Instant after, String type);

  Page<PersistentAuditEvent> findAllByAuditEventDateBetween(
      Instant fromDate, Instant toDate, Pageable pageable);

  List<PersistentAuditEvent> findByAuditEventDateBefore(Instant before);
}
