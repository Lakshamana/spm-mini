package br.ufpa.labes.spm.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpa.labes.spm.domain.Activity;
import br.ufpa.labes.spm.domain.ArtifactCon;
import br.ufpa.labes.spm.repository.ActivityRepository;
import br.ufpa.labes.spm.service.dto.ArtifactConDTO;

/** ArtifactConMapper */
@Service
public class ArtifactConMapper {

  Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired ActivityRepository activityRepository;

  public List<ArtifactConDTO> artifactconsToArtifactConDTOs(List<ArtifactCon> artifactcons) {
    return artifactcons.stream()
        .filter(Objects::nonNull)
        .map(this::artifactconToArtifactConDTO)
        .collect(Collectors.toList());
  }

  public ArtifactConDTO artifactconToArtifactConDTO(ArtifactCon artifactcon) {
    return new ArtifactConDTO(artifactcon);
  }

  public List<ArtifactCon> artifactConDTOsToArtifactCons(List<ArtifactConDTO> ArtifactConDTOs) {
    return ArtifactConDTOs.stream()
        .filter(Objects::nonNull)
        .map(this::artifactConDTOToArtifactCon)
        .collect(Collectors.toList());
  }

  public ArtifactCon artifactConDTOToArtifactCon(ArtifactConDTO artifactConDTO) {
    if (artifactConDTO == null) {
      return null;
    } else {
      Long pmid = artifactConDTO.getTheProcessModel().getId();
      List<Activity> activities = activityRepository.findByTheProcessModelId(pmid);
      log.debug("ACTIVITIES BY PMID: {}", activities);
      ArtifactCon artifactcon =
          (ArtifactCon)
              new ArtifactCon()
                  .toActivities(findActivities(artifactConDTO.getToActivities(), activities))
                  .fromActivities(findActivities(artifactConDTO.getFromActivities(), activities))
                  .theArtifact(artifactConDTO.getTheArtifact())
                  .theProcessModel(artifactConDTO.getTheProcessModel())
                  .ident(artifactConDTO.getIdent());
      // artifactcon.setId(artifactConDTO.getId());
      log.debug("ARTIFACTON OUT: {}", artifactcon);
      return artifactcon;
    }
  }

  private Set<Activity> findActivities(Set<Activity> source, List<Activity> lookupList) {
    return source == null ? null : source.stream()
      .map(act ->
        lookupList.stream()
          .filter(a -> a.getId().equals(act.getId()))
          .findFirst()
          .get())
      .collect(Collectors.toSet());
  }

  public ArtifactCon artifactconFromId(Long id) {
    if (id == null) {
      return null;
    }
    ArtifactCon artifactcon = new ArtifactCon();
    artifactcon.setId(id);
    return artifactcon;
  }
}
