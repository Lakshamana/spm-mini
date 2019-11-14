package br.ufpa.labes.spm.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.ufpa.labes.spm.domain.BranchANDCon;
import br.ufpa.labes.spm.service.dto.BranchANDConDTO;

/** BranchANDConMapper */
@Mapper
public interface BranchANDConMapper {
  BranchANDConMapper instance = Mappers.getMapper(BranchANDConMapper.class);

  BranchANDCon toBranchANDCon(BranchANDConDTO dto);

  BranchANDConDTO toBranchANDConDTO(BranchANDCon branchandCon);
}
