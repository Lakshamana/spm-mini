package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.Normal;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/** Spring Data repository for the Normal entity. */
@SuppressWarnings("unused")
@Repository
public interface NormalRepository extends GenericRepository<Normal, Long> {
  // @Query("")
  // List<RequiredPeople> find
}
