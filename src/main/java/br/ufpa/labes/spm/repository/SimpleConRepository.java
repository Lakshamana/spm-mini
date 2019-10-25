package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.SimpleCon;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/** Spring Data repository for the SimpleCon entity. */
@SuppressWarnings("unused")
@Repository
public interface SimpleConRepository extends GenericRepository<SimpleCon, Long> {}
