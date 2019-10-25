package br.ufpa.labes.spm.repository;
import br.ufpa.labes.spm.domain.MultipleCon;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MultipleCon entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MultipleConRepository extends GenericRepository<MultipleCon, Long> {

}
