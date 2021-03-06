package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.Plain;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/** Spring Data repository for the Plain entity. */
@SuppressWarnings("unused")
@Repository
public interface PlainRepository extends GenericRepository<Plain, Long> {}
