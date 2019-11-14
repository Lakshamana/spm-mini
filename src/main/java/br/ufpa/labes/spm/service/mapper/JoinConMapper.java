package br.ufpa.labes.spm.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.ufpa.labes.spm.domain.JoinCon;
import br.ufpa.labes.spm.service.dto.JoinConDTO;

/** JoinConMapper */
@Mapper
public interface JoinConMapper {
  JoinConMapper instance = Mappers.getMapper(JoinConMapper.class);

  JoinCon toJoinCon(JoinConDTO dto);

  JoinConDTO toJoinConDTO(JoinCon joinCon);
}
