package br.ufpa.labes.spm.repository;
import br.ufpa.labes.spm.domain.GraphicCoordinate;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GraphicCoordinate entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GraphicCoordinateRepository extends GenericRepository<GraphicCoordinate, Long> {

}
