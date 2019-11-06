package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.GraphicDescription;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/** Spring Data repository for the GraphicDescription entity. */
@SuppressWarnings("unused")
@Repository
public interface GraphicDescriptionRepository extends JpaRepository<GraphicDescription, Long> {}
