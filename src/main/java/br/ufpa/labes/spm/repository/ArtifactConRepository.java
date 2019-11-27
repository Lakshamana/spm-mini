package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.ArtifactCon;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/** Spring Data repository for the ArtifactCon entity. */
@Repository
public interface ArtifactConRepository extends GenericRepository<ArtifactCon, Long> {

  @Query(
      value =
          "select distinct artifactCon from ArtifactCon artifactCon"
              + " left join fetch artifactCon.toMultipleCons"
              + " left join fetch artifactCon.toActivities"
              + " left join fetch artifactCon.fromActivities",
      countQuery = "select count(distinct artifactCon) from ArtifactCon artifactCon")
  Page<ArtifactCon> findAllWithEagerRelationships(Pageable pageable);

  @Query(
      "select distinct artifactCon from ArtifactCon artifactCon left join fetch artifactCon.toMultipleCons"
          + " left join fetch artifactCon.toActivities left join fetch artifactCon.fromActivities")
  List<ArtifactCon> findAllWithEagerRelationships();

  @Query(
      "select artifactCon from ArtifactCon artifactCon left join fetch artifactCon.toMultipleCons"
          + " left join fetch artifactCon.toActivities left join fetch artifactCon.fromActivities"
          + " where artifactCon.id =:id")
  Optional<ArtifactCon> findOneWithEagerRelationships(@Param("id") Long id);
}
