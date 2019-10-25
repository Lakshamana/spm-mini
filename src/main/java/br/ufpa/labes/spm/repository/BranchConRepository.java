package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.BranchCon;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/** Spring Data repository for the BranchCon entity. */
@SuppressWarnings("unused")
@Repository
public interface BranchConRepository extends GenericRepository<BranchCon, Long> {}
