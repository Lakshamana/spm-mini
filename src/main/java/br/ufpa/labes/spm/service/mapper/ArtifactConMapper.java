package br.ufpa.labes.spm.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.ufpa.labes.spm.domain.ArtifactCon;
import br.ufpa.labes.spm.service.dto.ArtifactConDTO;

/** ArtifactConMapper */
@Mapper
public interface ArtifactConMapper {
  ArtifactConMapper instance = Mappers.getMapper(ArtifactConMapper.class);

  ArtifactCon toArtifactCon(ArtifactConDTO dto);

  ArtifactConDTO toArtifactConDTO(ArtifactCon artifactCon);
}
