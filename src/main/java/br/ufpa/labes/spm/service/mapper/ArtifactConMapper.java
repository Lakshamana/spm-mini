package br.ufpa.labes.spm.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.ufpa.labes.spm.domain.ArtifactCon;
import br.ufpa.labes.spm.service.dto.ArtifactConDTO;

/** ArtifactConMapper */
@Service
public class ArtifactConMapper {

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
      ArtifactCon artifactcon =
          (ArtifactCon)
              new ArtifactCon()
                  .toActivities(artifactConDTO.getToActivities())
                  .fromActivities(artifactConDTO.getFromActivities())
                  .theArtifact(artifactConDTO.getTheArtifact())
                  .theProcessModel(artifactConDTO.getTheProcessModel())
                  .ident(artifactConDTO.getIdent());
      artifactcon.setId(artifactConDTO.getId());
      return artifactcon;
    }
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
