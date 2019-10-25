package br.ufpa.labes.spm.repository;
import br.ufpa.labes.spm.domain.BranchConCondToMultipleCon;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the BranchConCondToMultipleCon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchConCondToMultipleConRepository extends GenericRepository<BranchConCondToMultipleCon, Long> {

}
