package br.ufpa.labes.spm.repository;

import br.ufpa.labes.spm.domain.Activity;
import br.ufpa.labes.spm.repository.interfaces.GenericRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/** Spring Data repository for the Activity entity. */
@Repository
public interface ActivityRepository extends GenericRepository<Activity, Long> {

  @Query("select distinct act from Activity act where act.theProcessModel.id = :pmid")
  List<Activity> findByTheProcessModelId(@Param("pmid") Long pmid);

  @Query(
      value =
          "select distinct activity from Activity activity left join fetch activity.toJoinCons left join fetch activity.fromBranchANDCons left join fetch activity.fromArtifactCons left join fetch activity.toArtifactCons",
      countQuery = "select count(distinct activity) from Activity activity")
  Page<Activity> findAllWithEagerRelationships(Pageable pageable);

  @Query(
      "select distinct activity from Activity activity left join fetch activity.toJoinCons "
          + "left join fetch activity.fromBranchANDCons left join fetch activity.toBranchCons "
          + "left join fetch activity.fromArtifactCons left join fetch activity.toArtifactCons "
          + "left join fetch activity.fromSimpleCons left join fetch activity.toSimpleCons")
  List<Activity> findAllWithEagerRelationships();

  @Query(
      "select distinct activity from Activity activity left join fetch activity.toJoinCons "
          + "left join fetch activity.fromBranchANDCons left join fetch activity.toBranchCons "
          + "left join fetch activity.fromArtifactCons left join fetch activity.toArtifactCons "
          + "left join fetch activity.fromSimpleCons left join fetch activity.toSimpleCons "
          + "where activity.id =:id")
  Optional<Activity> findOneWithEagerRelationships(@Param("id") Long id);
}
