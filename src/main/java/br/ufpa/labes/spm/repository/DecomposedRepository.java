package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.Decomposed;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/** Spring Data repository for the Decomposed entity. */
@SuppressWarnings("unused")
@Repository
public interface DecomposedRepository extends GenericRepository<Decomposed, Long> {}
